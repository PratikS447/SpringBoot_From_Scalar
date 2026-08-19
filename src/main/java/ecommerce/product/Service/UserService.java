package ecommerce.product.Service;

import ecommerce.product.Repository.UserRepository;
import ecommerce.product.dtos.UserRequest;
import ecommerce.product.dtos.UserResponse;
import ecommerce.product.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // CREATE
    public UserResponse createUser(UserRequest request){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }

    // READ ALL
    public List<UserResponse> getAllUser(){
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build()).toList();
    }

    // READ ONE
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id : "+id)
                );

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    // UPDATE
    public UserResponse updateUser(Long id, UserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("User not found with id : "+id)
                );
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .build();
    }

    // DELETE
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException(
                    "User not found with id: "+id
            );
        }
        userRepository.deleteById(id);
    }
}
