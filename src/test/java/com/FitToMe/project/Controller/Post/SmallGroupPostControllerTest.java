package com.FitToMe.project.Controller.Post;

import com.FitToMe.project.DTO.Post.SmallGroupPostDTO;
import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.SmallGroupPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Entity.UserAuthority;
import com.FitToMe.project.Repository.Post.SmallGroupPostRepository;
import com.FitToMe.project.Repository.UserRepository;
import com.FitToMe.project.Service.Post.SmallGroupPost.SmallGroupPostStatusService;
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
class SmallGroupPostControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SmallGroupPostRepository smallGroupPostRepository;

    @Autowired
    SmallGroupPostStatusService smallGroupPostStatusService;

    @Test
    @DisplayName("카테고리별 소모임 게시글 조회 Service")
    void readAllPostsByCategoryAndPaging() {
        //given
        User user = userRepository.save(
                User.builder()
                        .role(UserAuthority.NORMAL)
                        .password("password")
                        .email("email")
                        .nickname("nickname")
                        .build());

        int numOfDIYSmallGroupPost = 10;
        for (int i = 1; i <= numOfDIYSmallGroupPost; i++) {
            smallGroupPostRepository.save(
                    SmallGroupPost.builder()
                            .user(user)
                            .title("diy" + i)
                            .content("content")
                            .category(Category.DIY)
                            .build());
        }
        int numOfTravelSmallGroupPost = 3;
        for (int i = 1; i <= numOfTravelSmallGroupPost; i++) {
            smallGroupPostRepository.save(
                    SmallGroupPost.builder()
                            .user(user)
                            .title("travel" + i)
                            .content("content")
                            .category(Category.TRAVEL)
                            .build());
        }

        //when
        int pageSize = 5;
        Page<SmallGroupPostDTO> findPostsOfDIY = smallGroupPostStatusService.findAllByCategoryAndPaging(Category.DIY, 0, pageSize);
        Page<SmallGroupPostDTO> findPostsOfTravel = smallGroupPostStatusService.findAllByCategoryAndPaging(Category.TRAVEL, 0, pageSize);

        //then
        assertThat(findPostsOfDIY.getTotalElements()).isEqualTo(numOfDIYSmallGroupPost);
        assertThat(findPostsOfTravel.getTotalElements()).isEqualTo(numOfTravelSmallGroupPost);

        assertThat(findPostsOfDIY.getContent().get(0).getTitle()).isEqualTo("diy10");
        assertThat(findPostsOfDIY.getContent().size()).isEqualTo(pageSize);
    }
}