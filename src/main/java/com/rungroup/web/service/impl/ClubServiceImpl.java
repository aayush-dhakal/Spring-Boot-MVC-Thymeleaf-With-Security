package com.rungroup.web.service.impl;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.respositoy.ClubRepository;
import com.rungroup.web.respositoy.UserRepository;
import com.rungroup.web.security.SecurityUtil;
import com.rungroup.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.ClubMapper.mapToClub;
import static com.rungroup.web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();

        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);

//       we have to save the new data as club so we have to convert clubDto to club object
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());

    }

//    private Club mapToClub(ClubDto club) {
//        Club clubDto = Club.builder()
//                .id(club.getId())
//                .title(club.getTitle())
//                .photoUrl(club.getPhotoUrl())
//                .content(club.getContent())
//                .createdOn(club.getCreatedOn())
//                .updatedOn(club.getUpdatedOn())
//                .build();
//        return clubDto;
//    }
//
//    private ClubDto mapToClubDto(Club club) {
//        ClubDto clubDto = ClubDto.builder()
//                .id(club.getId())
//                .title(club.getTitle())
//                .photoUrl(club.getPhotoUrl())
//                .content(club.getContent())
//                .createdOn(club.getCreatedOn())
//                .updatedOn(club.getUpdatedOn())
//                .build();
//        return clubDto;
//    }
}
