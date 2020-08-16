#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/socket.h>
#include <netinet/ip.h>
#include <arpa/inet.h>

#define MAX_LINE 20

int main (int argc, char *argv[]) {
  char* host_addr = argv[1];
  int port = atoi(argv[2]);
  char* sequence_number = argv[3];
  /* Open a socket */
  int s;
  if((s = socket(PF_INET, SOCK_STREAM, 0)) <0){
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

  /* Connect to the server */
  if(connect(s, (struct sockaddr *)&sin, sizeof(sin))<0){
    perror("simplex-talk: connect");
    close(s);
    exit(1);
  }

  /*send Hello X to the server*/
  char buf[MAX_LINE];
  strcat(buf, "HELLO ");
  strcat(buf, sequence_number);
  buf[MAX_LINE-1] = '\0';
  int len = strlen(buf)+1;
  send(s, buf, len, 0);

  /*receive Hello Y from the server*/
  char h2[MAX_LINE];
  len = recv(s, h2, sizeof(h2), 0);
  puts(h2);
  fflush(stdout);
  int s1 = atoi(sequence_number);
  int s2 = atoi(h2 + 5);
  if (s2 == s1 + 1) {
    /*send third hand shake valor Z to the server*/
    int s3 = ++s2;
    char num3[MAX_LINE];
    sprintf(num3, "%d", s3);
    char h3[MAX_LINE];
    strcat(h3, "HELLO ");
    strcat(h3, num3);
    h3[MAX_LINE-1] = '\0';
    len = strlen(h3) + 1;
    send(s, h3, len, 0);
  } else {
    perror("ERROR");
    close(s);
    exit(1);
  }
  close(s);
  return 0;
}
