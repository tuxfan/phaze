/*----------------------------------------------------------------------------*
 * Phaze static interface
 *----------------------------------------------------------------------------*/

/* error codes */

static const int32_t PHAZE_SUCCESS = 0;
static const int32_t PHAZE_MALLOC_ERROR = -1;

/* constants */

#ifndef ALLOCATION_BLOCK_SIZE
	static const int32_t phaze_allocation_block_size = 16;
#else
	static const int32_t phaze_allocation_block_size = ALLOCATION_BLOCK_SIZE;
#endif

#ifndef PHAZE_CELL_DATA_ALIGNMENT
	static const int32_t phaze_cell_data_alignment = 128;
#else
	static const int32_t phaze_cell_data_alignment = PHAZE_CELL_DATA_ALIGNMENT;
#endif

/* initialization */

int32_t phz_init();

/* cell interface */

int32_t phz_add_cell();
int32_t phz_remove_cell(size_t cid);
int32_t phz_num_cells(size_t * count);

int32_t phz_get_cell(size_t cid, phz_cell cell);
int32_t phz_set_cell(size_t cid, const phz_cell cell);

int32_t phz_get_cell_volume(size_t cid, double * volume);
// do we need this?
//int32_t phz_set_cell_volume(size_t cid, const double * volume);

/* material interface */

int32_t phz_get_num_materials(phz_cell cell, size_t * count);

int32_t phz_get_material(phz_cell cell, size_t index,
	phz_material material);

int32_t phz_set_material(phz_cell cell, size_t index,
	phz_material material);

/* isotope interface */

#if 0
int32_t phz_num_isotopes(phz_material material, size_t * count);

int32_t phz_get_isotope(phz_material material, size_t * index,
	phz_isotope isotope);

int32_t phz_set_isotope(phz_material material, size_t * index,
	phz_isotope isotope);
#endif

/* filanlization */

int32_t phz_finalize();
