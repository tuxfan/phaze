/*----------------------------------------------------------------------------*
 * Phaze utilities
 *----------------------------------------------------------------------------*/

#ifndef phzutils_h
#define phzutils_h

#include <stdlib.h>
#include <string.h>

#include <phaze.h>

/*----------------------------------------------------------------------------*
 * Allocate cells
 *----------------------------------------------------------------------------*/

inline int32_t phzutils_alloc_cells(size_t count, cell_t ** cells) {
	void * tmp = NULL;
	int32_t err = posix_memalign(&tmp, phaze_cell_data_alignment,
		count*sizeof(cell_t));

	if(err != 0) {
		return PHAZE_MALLOC_ERROR;
	} // if

	*cells = (cell_t *)tmp;

	return 0;
} // phzutils_alloc_cells

/*----------------------------------------------------------------------------*
 * Reallocate cells
 *----------------------------------------------------------------------------*/

inline int32_t phzutils_realloc_cells(size_t size, size_t count,
	cell_t ** cells) {
	cell_t * tmp;
	int32_t err = PHAZE_SUCCESS;

	err = phzutils_alloc_cells(size+count, &tmp);

	if(err == PHAZE_SUCCESS) {
		// FIXME: Error check?
		memcpy(tmp, *cells, size*sizeof(cell_t));
	} // if

	return err;
} // phzutils_realloc_cells

#endif // phzutils_h
