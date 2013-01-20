/*----------------------------------------------------------------------------*
 * Phaze AoS implementation of static interface
 *----------------------------------------------------------------------------*/

#include <phzutils.h>

/* data */

phaze_t phz;

/* initialization */

int32_t phz_init() {
	int32_t err = 0;

	phz.cell_data_els = 0;
	err = phzutils_alloc_cells(phaze_allocation_block_size, &phz.cell_data);

	if(err != PHAZE_SUCCESS) {
		return err;
	} // if

	phz.cell_data_allocated = phaze_allocation_block_size;

	return PHAZE_SUCCESS;
} // phz_init

/* finalization */

int32_t phz_finalize() {
	return PHAZE_SUCCESS;
} // phz_finalize

/* cell interface */

int32_t add_cell(phz_cell cell) {
	if(phz.cell_data_els+1 > phz.cell_data_allocated) {
		int32_t err = phzutils_realloc_cells(phz.cell_data_els,
			phaze_allocation_block_size, &phz.cell_data);

		if(err != PHAZE_SUCCESS) {
			return err;
		} // if

		phz.cell_data_allocated += phaze_allocation_block_size;
	} // if

	cell = &phz.cell_data[phz.cell_data_els++];
} // add_cell
