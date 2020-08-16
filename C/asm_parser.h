/***************************************************************************
 * file name   : asm_parser.h                                              *
 * author      : Xiaoyu Gong                                               *
 * description : this header file declares the functions for those         *
 *               in the "asm_parser.c" library                             *
 *			     standard "string.h" C-library.                            * 
 ***************************************************************************/

#define ROWS 100
#define COLS 255

int read_asm_file(char* filename, char program[ROWS][COLS]);
// int parse_instruction (char* instr, char* instr_bin_str);
// int parse_reg (char reg_num, char* instr_bin_str);
// int parse_add (char* instr, char* instr_bin_str);
// int parse_mul (char* instr, char* instr_bin_str);
// int parse_xor (char* instr, char* instr_bin_str);
// unsigned short int str_to_bin (char* instr_bin_str);
// int write_obj_file (char* filename, unsigned short int program_bin[ROWS]);