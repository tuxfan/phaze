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

inline int32_t phzutils_allocate(size_t count, size_t typesize, void ** data) {
	void * tmp = NULL;
	int32_t err = posix_memalign(&tmp, phaze_data_alignment, count*typesize);

	if(err != 0) {
		return PHAZE_MALLOC_ERROR;
	} // if

	*data = tmp;

	return 0;
} // phzutils_alloc_cells

/*----------------------------------------------------------------------------*
 * Reallocate cells
 *----------------------------------------------------------------------------*/

inline int32_t phzutils_reallocate(size_t count, size_t newcount,
	size_t typesize, void ** data) {
	void * tmp;
	int32_t err = PHAZE_SUCCESS;

	err = phzutils_allocate(newcount, typesize, &tmp);

	if(err == PHAZE_SUCCESS) {
		memcpy(tmp, *data, count*typesize);
		free(*data);
		*data = tmp;
	} // if

	return err;
} // phzutils_realloc_cells

#endif // phzutils_h
