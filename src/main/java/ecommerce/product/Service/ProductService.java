package ecommerce.product.Service;

import ecommerce.product.dtos.GetProductDto;
import ecommerce.product.exceptions.NotFoundException;
import ecommerce.product.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService{

    public GetProductDto getProductUsingId(Long id) throws NotFoundException{
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://fakestoreapi.com/products/"+id;
        Product product = restTemplate.getForObject(url, Product.class);

        if (product == null){
            throw new NotFoundException();
        }

        return convertToDto(product);
    }

    private GetProductDto convertToDto(Product product){
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setTitle(product.getTitle());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setPrice(product.getPrice());
        return getProductDto;
    }

    public List<GetProductDto> getAllProduct(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://fakestoreapi.com/products";
        Product products[] = restTemplate.getForObject(url, Product[].class);

        List<GetProductDto> product_ls = new ArrayList<>();
        for (Product p: products){
            product_ls.add(convertToDto(p));
        }

        return product_ls;
    }
}