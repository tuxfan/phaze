/*----------------------------------------------------------------------------*
 * Phaze static interface
 *----------------------------------------------------------------------------*/

/* initialization */

int32_t phz_init();

/* allocate cells */

int32_t phz_add_cells(size_t cells);

/* cell interface */

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
