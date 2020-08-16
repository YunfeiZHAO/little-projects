#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/select.h>
#include <sys/socket.h>
#include <netinet/ip.h>
#include <arpa/inet.h>
#include <fcntl.h>

/* This is a reference socket server implementation that prints out the messages
 * received from clients. */

#define MAX_PENDING 10
#define MAX_LINE 20
#define MAX_CLIENT 100

typedef struct {
    int socket;
    int X;
    int assigned;
} entry;

int handle_first_shake(entry *e) {
    socklen_t len;
    char buf[MAX_LINE];
    memset(buf, '\0', sizeof(buf));
    //receive fisrt hand shake valor X from the client
    len = recv(e -> socket, buf, sizeof(buf), 0);
    puts(buf);
    fflush(stdout);
    int X = atoi(buf + 5);
    e -> X = X;

    //send second hand shake message Y to client
    int Y = X + 1;
    char num2[MAX_LINE];
    char h2[MAX_LINE];
    sprintf(num2, "%d", Y);
    strcat(h2, "HELLO ");
    strcat(h2, num2);
    h2[MAX_LINE-1] = '\0';
    len = strlen(h2)+1;
    send(e -> socket, h2, len, 0);
    e -> assigned = 1;
    return 0;
}

int handle_second_shake(entry *e, fd_set *rfds) {
    //receive third hand shake message Z from client
    char h3[MAX_LINE];
    memset(h3, '\0', sizeof(h3));
    int len = recv(e -> socket, h3, sizeof(h3), 0);
    int Z = atoi(h3 + 5);
    if(Z == e -> X + 2) {
        puts(h3);
        fflush(stdout);
    } else {
        perror("ERROR: second hand shake with wrong number");
        FD_CLR(e -> socket, rfds);
        close(e -> socket);
        e -> assigned = -1;
    }
    FD_CLR(e -> socket, rfds);
    close(e -> socket);
    e -> assigned = -1;
    return 0;
}

int main(int argc, char *argv[]) {
    char* host_addr = argv[1];
    int port = atoi(argv[2]);
    entry client_state_array[MAX_CLIENT];
    for(int i = 0; i < MAX_CLIENT; i++){
        client_state_array[i].assigned = -1;
    }

    /*setup passive open*/
    int s;
    if((s = socket(PF_INET, SOCK_STREAM, 0)) <0) {
        perror("simplex-talk: socket");
        exit(1);
    }
    /* Config the server address */
    struct sockaddr_in sin;
    sin.sin_family = AF_INET; 
    sin.sin_addr.s_addr = inet_addr(host_addr);
    sin.sin_port = htons(port);
    // Set all bits of the padding field to 0
    memset(sin.sin_zero, '\0', sizeof(sin.sin_zero));

    /* Bind the socket to the address */
    if((bind(s, (struct sockaddr*)&sin, sizeof(sin)))<0){
        perror("simplex-talk: bind");
        exit(1);
    }

    // connections can be pending if many concurrent client requests
    listen(s, MAX_PENDING);  
    int new_s;
    socklen_t len = sizeof(sin);

    fd_set rfds; // need to be initialized everytime we call p/select
    FD_ZERO(&rfds);
    fcntl(s, F_SETFL, O_NONBLOCK);
    FD_SET(s, &rfds);

    while (1) {
        fflush(stdout);
        int max_fd = s;
		for(int i = 0; i< MAX_CLIENT; i++) {
			if(client_state_array[i].assigned >= 0 && client_state_array[i].socket > max_fd) {
				max_fd = client_state_array[i].socket; }}

        fd_set new_rfds = rfds;

        int res_select = pselect(max_fd+1, &new_rfds, NULL, NULL, NULL, NULL);
		if(res_select < 0) {
			perror("pselect");
			exit(1);
		} else if(res_select == 0) { 
            fputs("no action\n", stdout);
            fflush(stdout);
            continue; 
        } else if(FD_ISSET(s, &new_rfds)) {
            /* 1, request for new connection: accept new client */
            if((new_s = accept(s, (struct sockaddr *)&sin, &len)) <0) {
                perror("simplex-talk: accept");
                exit(1);
            }
            fcntl(new_s, F_SETFL, O_NONBLOCK);
            int i;
            for(i = 0; i < MAX_CLIENT; i++) {
                if(client_state_array[i].assigned == -1) {
                    client_state_array[i].assigned = 0;
                    client_state_array[i].socket = new_s;
                    FD_SET(new_s, &rfds);
                    break;
                }
            }
            if(i == MAX_CLIENT) {
                perror("client_state_array: no more space");
                exit(1);
            }
        } else {
            /* 2, two handle functions*/			    
            for(int i = 0; i < MAX_CLIENT; i++) {
                if(client_state_array[i].assigned < 0) {continue;}
                else if(FD_ISSET(client_state_array[i].socket, &new_rfds)) {
                    if(client_state_array[i].assigned == 0) {
                        handle_first_shake(&client_state_array[i]);
                    } else if(client_state_array[i].assigned == 1) {
                        handle_second_shake(&client_state_array[i], &rfds);
                    } else {
                        perror("client_state_array: bad state, no socked in this entry");
                    }
                }
            }
        }
    }
    return 0;
}