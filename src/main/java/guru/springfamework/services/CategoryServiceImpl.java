package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    //returning CategoryDTOs in the HTTP response, so have to use Mapper (from MapStruct)
    //SERVICE converts from Category to DTO and passes up to controller
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.categoryToCategoryDTO(category))
                .collect(Collectors.toList());
    }

    //returning CategoryDTOs in the HTTP response, so have to use Mapper (from MapStruct)
    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
