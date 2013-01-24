/*----------------------------------------------------------------------------*
 * First pass at phaze test program
 *----------------------------------------------------------------------------*/

#include <iostream>
#include <phaze.h>

static const size_t NxN(25);

int main(int argc, char ** argv) {

	std::cerr << "Calling Phaze initialization..." << std::endl;
	phz_init();

	phz_cell cell;
	for(size_t i(0); i<NxN; ++i) {
		phz_add_cell(&cell);

		size_t id;
		int32_t err = phz_get_cell_id(cell, &id);
		std::cerr << "Added cell id(" << id << ")" << std::endl;
	} // for

	std::cerr << "Calling Phaze finalization..." << std::endl;
	phz_finalize();

	return 0;
} // main
