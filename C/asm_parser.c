/***************************************************************************
 * file name   : asm_parser.c                                              *
 * author      : Xiaoyu Gong                                               *
 * description : the functions are declared in asm_parser.h                *
 *               The intention of this library is to parse a .ASM file     *
 ***************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "asm_parser.h"

int read_asm_file (char* filename, char program[ROWS][COLS]) {
    FILE *file = fopen(filename, "r");
    int i = 0;
    int j = 0;
    char str[COLS];
    if (file == NULL) {
        fprintf (stderr, "error2: read_asm_file() failed\n");
        return 2;
    }
    while (fgets(str, file) != NULL) {
        j = 0;
        while(str[j] != '\n') {
            program[i][j] = str[j];
            j++;
        }
        i++;
    }
    return 0;
}

// int parse_instruction (char* instr, char* instr_bin_str) {
//     FILE *file;
//     char str[15];
//     char program_bin_str[100][255];
//     file = fopen(filename, "r");
//     if (file == NULL) {
//         printf("error3: parse_instruction() failed");
//         return 1;
//     }
//     if (program[0][0] == 'A' && program[0][1] == 'D' && program[0][2] == 'D') {
//         parse_add(instr, instr_bin_str);
//     }
//     else {
//         for (int i = 0; i < sizeof(int); i++) {
//             program_bin_str[0] = instr_bin_str;
//             str[0] = instr;
//             program_bin_str[i] = str[i];
//         }
//     }
//     return 0;
// }

// int parse_reg (char reg_num, char* instr_bin_str) {
//     switch(reg_num) {
//         case '0':
//             instr_bin_str[4] = 0;
//             instr_bin_str[5] = 0;
//             instr_bin_str[6] = 0;
//             break;
//         case '1':
//             instr_bin_str[4] = 0;
//             instr_bin_str[5] = 0;
//             instr_bin_str[6] = 1;
//             break;
//         case '2':
//             instr_bin_str[4] = 0;
//             instr_bin_str[5] = 1;
//             instr_bin_str[6] = 0;
//             break;
//         case '3':
//             instr_bin_str[4] = 0;
//             instr_bin_str[5] = 1;
//             instr_bin_str[6] = 1;
//             break;
//         case '4':
//             instr_bin_str[4] = 1;
//             instr_bin_str[5] = 0;
//             instr_bin_str[6] = 0;
//             break;
//         case '5':
//             instr_bin_str[4] = 1;
//             instr_bin_str[5] = 0;
//             instr_bin_str[6] = 1;
//             break;
//         case '6':
//             instr_bin_str[4] = 1;
//             instr_bin_str[5] = 1;
//             instr_bin_str[6] = 0;
//             break;
//         case '7':
//             instr_bin_str[4] = 1;
//             instr_bin_str[5] = 1;
//             instr_bin_str[6] = 1;
//             break;
//     }
//     return 0;
// }

// int parse_add (char* instr, char* instr_bin_str) {
//     if (instr_bin_str == NULL) {
//         printf("error4: parse_add() failed");
//     }
//     instr_bin_str[0] = 0;
//     instr_bin_str[1] = 0;
//     instr_bin_str[2] = 0;
//     instr_bin_str[3] = 1;
//     instr_bin_str[10] = 0;
//     instr_bin_str[11] = 0;
//     instr_bin_str[12] = 0;
//     for (int i = 0; i < sizeof(int); i++) {
//         program_bin_str[0] = instr_bin_str;
//         str[0] = instr;
//         program_bin_str[i] = str[i];
//     }
// }

// int parse_mul (char* instr, char* instr_bin_str) {
//     if (instr_bin_str == NULL) {
//         printf("error5: parse_mul() failed");
//     }
//     instr_bin_str[0] = 0;
//     instr_bin_str[1] = 0;
//     instr_bin_str[2] = 0;
//     instr_bin_str[3] = 1;
//     instr_bin_str[10] = 0;
//     instr_bin_str[11] = 0;
//     instr_bin_str[12] = 1;
//     for (int i = 0; i < sizeof(int); i++) {
//         program_bin_str[0] = instr_bin_str;
//         str[0] = instr;
//         program_bin_str[i] = str[i];
//     }
// }

// int parse_xor (char* instr, char* instr_bin_str) {
//     if (instr_bin_str == NULL) {
//         printf("error6: parse_xor() failed");
//     }
//     instr_bin_str[0] = 0;
//     instr_bin_str[1] = 1;
//     instr_bin_str[2] = 0;
//     instr_bin_str[3] = 1;
//     instr_bin_str[10] = 0;
//     instr_bin_str[11] = 1;
//     instr_bin_str[12] = 1;
//     for (int i = 0; i < sizeof(int); i++) {
//         program_bin_str[0] = instr_bin_str;
//         str[0] = instr;
//         program_bin_str[i] = str[i];
//     }
// }

// unsigned short int str_to_bin (char* instr_bin_str) {
//     char *p;
//     long r;
//     r = strtol(instr_bin_str, &p, 10);
//     printf("0x%x\n", r);
//     while (r[i] != NULL) {
//         program_bin[i] = r[i];
//     }
// }

// int write_obj_file (char* filename, unsigned short int program_bin[ROWS]) {
//     FILE *file;
//     filename[-1] = 'j';
//     filename[-2] = 'b';
//     filename[-3] = 'o';
//     file = fopen(filename, "rb");
//     if (file == NULL) {
//         printf("error7: write_obj_file() failed");
//     }
//     printf("0xCADE\n");
//     int i = 0;
//     while (program_bin[i] != NULL) {
//         i++;
//     }
//     for (int j = 0; j < i; j++) {
//         printf(program_bin[j]);
//     }
//     fclose(file);
//     return 0;
// }