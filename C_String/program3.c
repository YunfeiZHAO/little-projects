/*************************************************************************/
/* File Name : program3.c 											                         */
/* Purpose   : this C-file tests the functions of the "my_string" library*/
/* Author(s) : Xiaoyu Gong                    											     */
/*************************************************************************/

#include <stdio.h>
#include <string.h>
#include "my_string.h"

int main() {
	char my_string[100] = "Tom" ;
	printf ("Reversed string my_strrev() = %s\n", my_strrev(my_string));
	printf ("Converted letter case my_strccase() = %s\n", my_strccase(my_string));
	return 0;
}
int main (int ​argc​, char** ​argv​) { 
 printf ("# of arguments passed: %d\n", argc) ; 
 for (int i=0; i< argc ; i++) { printf ( "argv[%d] = %s\n", i, argv[i] ) ; } 
 return (0) ; }