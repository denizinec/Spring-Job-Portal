package com.denizci155.jobportal.service;

import com.denizci155.jobportal.entity.JobSeekerProfile;
import com.denizci155.jobportal.entity.RecruiterProfile;
import com.denizci155.jobportal.entity.Users;
import com.denizci155.jobportal.repository.JobSeekerProfileRepository;
import com.denizci155.jobportal.repository.RecruiterProfileRepository;
import com.denizci155.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, RecruiterProfileRepository recruiterProfileRepository,
                        JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.usersRepository = usersRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;

    }

        public  Users addNew(Users users){
            users.setActive(true);
            users.setRegistrationDate(new Date(System.currentTimeMillis()));
            users = usersRepository.save(users);
            int userTypeId = users.getUserTypeId().getUserTypeId();
            if (userTypeId == 1){
                recruiterProfileRepository.save(new RecruiterProfile(users));
            } else {
                jobSeekerProfileRepository.save(new JobSeekerProfile(users));
            }
            return users;


        }

        public Optional<Users> getUserByEmail(String email){

            return usersRepository.findByEmail(email);

        }

}
