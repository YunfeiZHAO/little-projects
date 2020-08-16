#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/socket.h>
#include <netinet/ip.h>
#include <arpa/inet.h>

/* This is a reference socket server implementation that prints out the messages
 * received from clients. */

#define MAX_PENDING 10
#define MAX_LINE 20

int main(int argc, char *argv[]) {
  char* host_addr = argv[1];
  int port = atoi(argv[2]);

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

  /* wait for connection, then receive and print text */
  int new_s;
  socklen_t len = sizeof(sin);
  char buf[MAX_LINE];
  while(1){
    if((new_s = accept(s, (struct sockaddr *)&sin, &len)) <0){
      perror("simplex-talk: accept");
      exit(1);
    }

    //receive fisrt hand shake valor X from the client
    len = recv(new_s, buf, sizeof(buf), 0);
    puts(buf);
    fflush(stdout);
    int X = atoi(buf + 5);

    //send second hand shake message Y to client
    int Y = X + 1;
    char num2[MAX_LINE];
    char h2[MAX_LINE];
    sprintf(num2, "%d", Y);
    strcat(h2, "HELLO ");
    strcat(h2, num2);
    h2[MAX_LINE-1] = '\0';
    len = strlen(h2)+1;
    send(new_s, h2, len, 0);

    //receive third hand shake message Z from client
    char h3[MAX_LINE];
    len = recv(new_s, h3, sizeof(h3), 0);
    int Z = atoi(h3 + 5);
    if(Z == Y + 1) {
      puts(h3);
      fflush(stdout);
    } else {
      perror("ERROR");
    }
    close(new_s);
  }
  return 0;
}
