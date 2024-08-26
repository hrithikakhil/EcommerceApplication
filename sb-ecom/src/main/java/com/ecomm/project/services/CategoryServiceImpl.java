package com.ecomm.project.services;

import com.ecomm.project.dtos.CategoryDto;
import com.ecomm.project.dtos.CategoryResponse;
import com.ecomm.project.exceptions.APIException;
import com.ecomm.project.exceptions.ResourceNotFoundException;
import com.ecomm.project.models.Category;
import com.ecomm.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

//    private Long nextId = 1L;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

//        List<Category> listOfAllCategories = categoryRepository.findAll();
        List<Category> listOfAllCategories = categoryPage.getContent();
        if (listOfAllCategories.isEmpty()) {
            throw new APIException("No categories found");
        }

        List<CategoryDto> categoryDtos = listOfAllCategories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDtos);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDb != null) {
            throw new APIException("Category with name " + category.getCategoryName() + " already exists !!!");
        }
//        category.setCategoryId(nextId++);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto deleteCategory(Long categoryId) {
//        Category category = categories.stream().
//                filter(c  -> c.getCategoryId().equals(categoryId)).
//                findFirst().get();
        // Below orElse is used to handle null/Bad requests.
        // (For example if users requests to delete the id that doesnt exist)

//        Category category = categories.stream().
//                filter(c  -> c.getCategoryId().equals(categoryId)).
//                findFirst().orElse(null);
//        List<Category> categories = categoryRepository.findAll();
//
//        Category category = categories.stream().
//                filter(c  -> c.getCategoryId().equals(categoryId)).
//                findFirst().
//                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//
//        if(category == null) {
//          return "Category not found";
//         }
//
//        categoryRepository.delete(category);
//        return "Category with categoryId " + categoryId +" deleted successfully";

        // Below is the optimized code
//        Category Category = categoryRepository.findById(categoryId).
//                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//        categoryRepository.delete(Category);
//        return "Category with categoryId " + categoryId +" deleted successfully";


        Category Category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        categoryRepository.delete(Category);
        return modelMapper.map(Category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
//        List<Category> categories = categoryRepository.findAll();
//        Optional<Category> optionalCategory = categories.stream().
//                filter(c->c.getCategoryId().equals(categoryId)).
//                findFirst();
//        if(optionalCategory.isPresent()) {
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return savedCategory;
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//        }


        //Below Code is the optimized code

//        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
//        Category savedCategory = savedCategoryOptional.
//                                    orElseThrow(() ->
//                                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//        category.setCategoryId(categoryId);
//        savedCategory = categoryRepository.save(category);
//        return savedCategory;

        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional.
                orElseThrow(() ->
                        new ResourceNotFoundException("Category", "CategoryId", categoryId));
        Category categorytoSave = modelMapper.map(categoryDto, Category.class);
        categorytoSave.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(categorytoSave);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }
}
