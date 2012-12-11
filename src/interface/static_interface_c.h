/*----------------------------------------------------------------------------*
 * Phaze static interface
 *----------------------------------------------------------------------------*/

/* cell interface */

int32_t phz_get_cell(size_t cid, cell_data_t * cell);

int32_t phz_set_cell(size_t cid, cell_data_t * cell);

/* material interface */

int32_t phz_num_materials(cell_data_t * cell, size_t * count);

int32_t phz_get_material(cell_data_t * cell, size_t index,
	material_t * material);

int32_t phz_set_material(cell_data_t * cell, size_t index,
	material_t * material);

/* isotope interface */

int32_t phz_num_isotopes(material_t * material, size_t * count);

int32_t phz_get_isotope(material_t * material, size_t * index,
	isotope_t * isotope);

int32_t phz_set_isotope(material_t * material, size_t * index,
	isotope_t * isotope);
