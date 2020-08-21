package com.saif.nisum.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saif.nisum.exception.UserManagementServiceErrorCodes;
import com.saif.nisum.exception.UserManagementServiceException;
import com.saif.nisum.model.UserDTO;
import com.saif.nisum.repository.UserRepository;

@Service
public class UserService {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static final Pattern VALID_PASSWORD_REGEX = Pattern
			.compile("^(?=(?:.*[A-Z]){2})(?=.*[a-z])(?=(?:.*[0-9]){2}).*");

	@Autowired
	UserRepository userRepo;

	public UserDTO getId(String id) throws UserManagementServiceException {

		Optional<UserDTO> response = userRepo.findById(id);

		if (Objects.isNull(response) || !response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.USERID_NOT_FOUND);
		}

		return response.get();

	}

	public List<UserDTO> get() throws UserManagementServiceException {

		List<UserDTO> serviceResponse = new ArrayList<UserDTO>();

		userRepo.findAll().forEach(UserDTO -> serviceResponse.add(UserDTO));

		return serviceResponse;
	}

	public UserDTO create(UserDTO userDTO) throws UserManagementServiceException {

		System.out.println(userDTO);

		validateEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
		validateEmailAlreadyExist(userDTO.getEmail());

		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

		userDTO.setUserId(UUID.randomUUID().toString());
		userDTO.setToken(UUID.randomUUID().toString());
		userDTO.setCreated(currentDate);
		userDTO.setModified(currentDate);
		userDTO.setLastLogin(currentDate);
		userDTO.setIsactive(true);

		System.out.println(userDTO);

		return userRepo.save(userDTO);
	}

	public UserDTO update(UserDTO userDTO) throws UserManagementServiceException {

		Optional<UserDTO> response = userRepo.findById(userDTO.getUserId());

		if (Objects.isNull(response) || !response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.USERID_NOT_FOUND);
		}

		validateEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());

		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

		UserDTO oldRecord = response.get();

		oldRecord.setName(userDTO.getName());
		oldRecord.setModified(currentDate);
		oldRecord.setLastLogin(currentDate);
		oldRecord.setIsactive(userDTO.isIsactive());
		oldRecord.setPhones(userDTO.getPhones());


		return userRepo.save(oldRecord);

	}

	public void validateEmailAndPassword(String userEmail, String UserPassword) throws UserManagementServiceException {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);

		if (!matcher.matches()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.CRITERIA_NOT_FOUND);
		}

		matcher = VALID_PASSWORD_REGEX.matcher(UserPassword);

		if (!matcher.matches()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.CRITERIA_NOT_FOUND);

		}
	}

	public void validateEmailAlreadyExist(String userEmail) throws UserManagementServiceException {
		Optional<UserDTO> response = userRepo.findByEmail(userEmail);

		if (response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.USER_ALREADY_EXISTS);
		}
	}

}
