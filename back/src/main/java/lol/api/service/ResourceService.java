package lol.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lol.api.exception.BadRequestException;
import lol.api.exception.ResourceNotFoundException;
import lol.api.model.UserModel;
import lol.api.payload.AddUser;
import lol.api.repositories.UserRepository;
import lol.api.utils.CustomBeanUtils;

@Service
public class ResourceService {

  @Autowired
  private UserRepository userRepository;


  public UserModel add(AddUser user) {
    if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
      throw new BadRequestException("Email already in use");
    }

    if (userRepository.existsByUsernameIgnoreCase(user.getUsername())) {
      throw new BadRequestException("Username already in use");
    }

    UserModel applicationuser = new UserModel();
    BeanUtils.copyProperties(user, applicationuser, CustomBeanUtils.getNullPropertyNames(user));

    return userRepository.saveAndFlush(applicationuser);
  }

  public UserModel find(Long userid) {
    UserModel applicationuser = userRepository.findById(userid)
        .orElseThrow(() -> new ResourceNotFoundException("ApplicationUser not found"));

    return applicationuser;
  }

  public List<UserModel> findAll() {
    List<UserModel> applicationusers = userRepository.findAll();

    return applicationusers;
  }

}