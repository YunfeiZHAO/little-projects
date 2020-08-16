#########################################################
#  file name   : Makefile                               #
#  author      : Xiaoyu Gong                            #
#  description : Makefile to automate compiling         #
#                and linking                            #
#########################################################

all: assembler
assembler:
	clang -o assembler asm_parser.o assembler.o
assembler.o: assembler.c asm_parser.h
	clang -c assembler.c
asm_parser.o: asm_parser.c asm_parser.h
	clang -c asm_parser.c
clean:
	rm -rf *.o

clobber: clean
	rm -rf assembler