/*************************************************************************/
/* File Name : program1.c 											                         */
/* Purpose   : this C-file tests the functions of the "my_string" library*/
/* Author(s) : Xiaoyu Gong                    											     */
/*************************************************************************/

#include <stdio.h>
#include <string.h>
#include "my_string.h"

int main() {
	char my_string[100] = "Tom";
  
	printf ("Sample string: \"%s\"\n", my_string);
	printf ("String's length: strlen() = %lu \n", strlen(my_string));
	printf ("String's length: my_strlen() = %lu \n", my_strlen(my_string));
	printf ("String's length: my_strlen2() = %lu \n", my_strlen2(my_string));
  
  /* CODE HERE */
  
	return 0;
}