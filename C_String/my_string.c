/*************************************************************************/
/* File Name : my_string.c 											                         */
/* Purpose   : This C-file defines the functions previously declared	   */
/*             in the my_string.h header file. 				           		     */
/* Author(s) : Xiaoyu Gong                    											     */
/*************************************************************************/

#include "my_string.h"

/* functions to determine the length of a string */

size_t2 my_strlen (const char *str) {
	size_t2 len = 0 ;
	while (str[len] != '\0') {
		len++; 
	}
	return len;
}

size_t2 my_strlen2 (const char *str) {
	const char* s;
	for (s = str; *s; ++s);
	return (s - str);
}

/* functions to copy a string */

char* my_strcpy  (char *dest, const char *src) {
  assert(dest != NULL && src != NULL);
  if (dest == NULL || src == NULL)
    return NULL;
  char *r = dest;
  while ((*dest++ = *src++) != '\0');
  return r;
}

char* my_strcpy2 (char *dest, const char *src) {
  assert(dest != NULL && src != NULL);
  if (dest == NULL || src == NULL)
    return NULL;
  char* r = dest;
  for (int i = 0; src[i] != '\0'; i++) {
    dest[i] = src[i];
  }
  dest[i] = '\0';
  return r;
}

/* functions to search a string */

char* my_strchr  (const char *str, int c) {
  while (*str != '\0') {
    if (*str == c)
			return str;
		str++;
	}
	return NULL;
}

char* my_strchr2 (const char *str, int c) {
  assert(str);
	const char *r = str;
	while (*r) {
		if (*r == c)
			return (char *)r;
		else
			r++;
	}
	return NULL;
}

/* functions to concatenate string */

char* my_strcat  (char *dest, const char *src) {
  char *r = dest;
  while (*r != '\0') {
    r++;
  }
  while (*src != '\0') {
    *r = *src;
    r++;
    src++;
  }
  *r = '\0';
  return dest;
}

char* my_strcat2 (char *dest, const char *src) {
  char *r;
  char *s;
  r = (char*)malloc(strlen(dest) + strlen(src) + 1);
  s = (char*)malloc(strlen(dest) + strlen(src) + 1);
  while (*dest)
    *s++ = *dest++;
  while (*src)
    *s++ = *src++;
  *s = '';
  return r;
}

/* functions to compare two strings */

int my_strcmp  (const char *str1, const char *str2) {
  while(*str1 != '\0' || *str2 != '\0') {
    if (*str1 == *str2) {
      str1++;
      str2++;
    }
    if(*str1 > *str2)
      return 1;
    if(*str1 < *str2)
      return -1;
  }
  return 0;
}

int my_strcmp2 (const char *str1, const char *str2) {
  assert(str1);
  assert(str2);
	while (*str1 == *str2) {
    if(*str1 == '\0')
      return 0;
    str1++;
    str2++;
  }
  if (*str1 > *str2)
    return 1;
  if (*str1 < *str2)
    return -1;
}

/* function to reverse a string */

char* my_strrev (char *str) {
  char *r = str;
  char *l = str;
  char c;
  while (*r)
    r++;
  r--;
  while (l < r) {
    c = *l;
    *l++ = *r;
    *r-- = c;
  }
  return(str);
}

/* function to convert a string to opposite case */

char* my_strccase (char *str) {
  for (int i = 0; i < strlen(str); i++) {
    if (*str[i] <= 'z' && *str[i] >= 'a') {
      str[i] -= 32;
    }
    if (*str[i] <= 'Z' && *str[i] >= 'A') {
      str[i] += 32;
    }
  }
  return (str);
}