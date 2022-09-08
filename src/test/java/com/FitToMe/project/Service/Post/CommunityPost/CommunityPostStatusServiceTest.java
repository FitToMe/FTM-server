package com.FitToMe.project.Service.Post.CommunityPost;

import com.FitToMe.project.DTO.Post.CommunityPostDTO;
import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.CommunityPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Entity.UserAuthority;
import com.FitToMe.project.Repository.Post.CommunityPostRepository;
import com.FitToMe.project.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommunityPostStatusServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityPostRepository communityPostRepository;

    @Autowired
    CommunityPostStatusService communityPostStatusService;

    @Test
    @DisplayName("카테고리별 커뮤니티 게시글 조회 Service")
    void findAllByCategoryAndPaging() {
        //given
        User user = userRepository.save(
                User.builder()
                        .role(UserAuthority.NORMAL)
                        .password("password")
                        .email("email")
                        .nickname("nickname")
                        .build());

        int numOfDIYCommunityPost = 10;
        for (int i = 1; i <= numOfDIYCommunityPost; i++) {
            communityPostRepository.save(
                    CommunityPost.builder()
                            .user(user)
                            .title("diy" + i)
                            .content("content")
                            .category(Category.DIY)
                            .build());
        }
        int numOfTravelCommunityPost = 3;
        for (int i = 1; i <= numOfTravelCommunityPost; i++) {
            communityPostRepository.save(
                    CommunityPost.builder()
                            .user(user)
                            .title("travel" + i)
                            .content("content")
                            .category(Category.TRAVEL)
                            .build());
        }

        //when
        int pageSize = 5;
        Page<CommunityPostDTO> findPostsOfDIY = communityPostStatusService.findAllByCategoryAndPaging(Category.DIY, 0, pageSize);
        Page<CommunityPostDTO> findPostsOfTravel = communityPostStatusService.findAllByCategoryAndPaging(Category.TRAVEL, 0, pageSize);

        //then
        assertThat(findPostsOfDIY.getTotalElements()).isEqualTo(numOfDIYCommunityPost);
        assertThat(findPostsOfTravel.getTotalElements()).isEqualTo(numOfTravelCommunityPost);

        assertThat(findPostsOfDIY.getContent().get(0).getTitle()).isEqualTo("diy10");
        assertThat(findPostsOfDIY.getContent().size()).isEqualTo(pageSize);
    }
}