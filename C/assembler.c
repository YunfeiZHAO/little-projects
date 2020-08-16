/***************************************************************************
 * file name   : assembler.c                                               *
 * author      : Xiaoyu Gong                                               *
 * description : This program will assemble a .ASM file into a .OBJ file   *
 *               This program will use the "asm_parser" library to achieve *
 *			     its functionality.										   * 
 ***************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "asm_parser.h"

int main(int argc, char** argv) {
    int i = 0;
    int j = 0;
	char* filename = NULL; // name of ASM file
	char program[ROWS][COLS]; // ASM file line-by-line
	char program_bin_str[ROWS][17]; // instructions converted to a binary string
	unsigned short int program_bin[ROWS]; // instructions in binary (HEX)
    if (argc != 2) {
        fprintf (stderr, "error1: usage: ./assembler <assembly_file.asm>\n");
        return 1;
    }
    else {
        filename = argv[1];
        printf("%s\n", filename);
        //sscanf(argv[1], "%s", filename);
    }
    read_asm_file(filename, program);
    while (program[i] != '\0') {
        j = 0;
        while(program[i][j] != '\0') {
            printf("%c ", program[i][j]);
            j++;
        }
        printf("\n");
        i++;
    }
	// parse_instruction(instr, instr_bin_str);
	// str_to_bin(instr_bin_str);
}