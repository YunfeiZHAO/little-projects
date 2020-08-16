/*************************************************************************/
/* File Name : program2.c 											                         */
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