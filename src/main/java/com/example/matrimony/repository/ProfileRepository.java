package com.example.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.matrimony.domain.Profile;

@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    List<Profile> findByUsernameNot(String username);
}
