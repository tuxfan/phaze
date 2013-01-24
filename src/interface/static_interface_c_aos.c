/*----------------------------------------------------------------------------*
 * Phaze AoS implementation of static interface
 *----------------------------------------------------------------------------*/

#include <phzutils.h>

/* data */

phaze_t phz;

/*----------------------------------------------------------------------------*
 * Initialization
 *----------------------------------------------------------------------------*/

int32_t phz_init() {
	int32_t err = 0;

	// cells
	phz.cell_data_els = 0;
	err = phzutils_allocate(phaze_allocation_block_size, sizeof(cell_t),
		(void **)&phz.cell_data);

	if(err != PHAZE_SUCCESS) {
		return err;
	} // if

	phz.cell_data_allocated = phaze_allocation_block_size;

	// materials
	phz.material_data_els = 0;
	err = phzutils_allocate(phaze_allocation_block_size, sizeof(material_t),
		(void **)&phz.material_data);

	if(err != PHAZE_SUCCESS) {
		return err;
	} // if

	phz.material_data_allocated = phaze_allocation_block_size;

	return PHAZE_SUCCESS;
} // phz_init

/*----------------------------------------------------------------------------*
 * Finalization
 *----------------------------------------------------------------------------*/

int32_t phz_finalize() {
	free(phz.cell_data);
	free(phz.material_data);
	return PHAZE_SUCCESS;
} // phz_finalize

/*----------------------------------------------------------------------------*
 * Cell count
 *----------------------------------------------------------------------------*/

inline int32_t phz_num_cells(size_t * count) {
	*count = phz.cell_data_els;
	return 0;
} // phz_num_cells

/*----------------------------------------------------------------------------*
 * Add cell
 *----------------------------------------------------------------------------*/

int32_t phz_add_cell(phz_cell * cell) {
	// add memory if necessary
	if(phz.cell_data_els+1 > phz.cell_data_allocated) {
		int32_t err = phzutils_reallocate(phz.cell_data_els,
			phz.cell_data_els+phaze_allocation_block_size, sizeof(cell_t),
			(void **)&phz.cell_data);

		if(err != PHAZE_SUCCESS) {
			return err;
		} // if

		// update data allocated
		phz.cell_data_allocated += phaze_allocation_block_size;
	} // if

	// set return pointer
	*cell = &phz.cell_data[phz.cell_data_els];

	// use return pointer to update cell state
	// FIXME: we probably need a more sophisticated method of
	// assigning ids
	(*cell)->_phz_id = phz.cell_data_els;
	(*cell)->_phz_num_materials = 0;
	(*cell)->_phz_material_data = NULL;

	// increment cell count
	++phz.cell_data_els;

	return PHAZE_SUCCESS;
} // phz_add_cell

/*----------------------------------------------------------------------------*
 * Add material
 *----------------------------------------------------------------------------*/

int32_t phz_add_material(phz_cell cell, phz_material * material) {
	// add memory if necessary
	if(phz.material_data_els+1 > phz.material_data_allocated) {
		int32_t err = phzutils_reallocate(phz.material_data_els,
			phz.material_data_els+phaze_allocation_block_size,
			sizeof(material_t), (void **)&phz.material_data);

		if(err != PHAZE_SUCCESS) {
			return err;
		} // if

		// update data allocated
		phz.material_data_allocated += phaze_allocation_block_size;
	} // if
} // phz_add_material
