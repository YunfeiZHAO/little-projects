#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/socket.h>
#include <netinet/ip.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]) {
    char a[] = "fuck"; 
    printf(a+1);


    return 0;
}