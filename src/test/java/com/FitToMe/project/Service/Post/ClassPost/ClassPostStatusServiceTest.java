package com.FitToMe.project.Service.Post.ClassPost;

import com.FitToMe.project.DTO.Post.ClassPostDTO;
import com.FitToMe.project.Entity.Category;
import com.FitToMe.project.Entity.Post.ClassPost;
import com.FitToMe.project.Entity.User;
import com.FitToMe.project.Entity.UserAuthority;
import com.FitToMe.project.Repository.Post.ClassPostRepository;
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
class ClassPostStatusServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassPostRepository classPostRepository;

    @Autowired
    ClassPostStatusService classPostStatusService;

    @Test
    @DisplayName("카테고리별 클래스 게시글 조회 Service")
    void findAllByCategoryAndPaging() {
        //given
        User user = userRepository.save(
                User.builder()
                        .role(UserAuthority.NORMAL)
                        .password("password")
                        .email("email")
                        .nickname("nickname")
                        .build());

        int numOfDIYClassPost = 10;
        for (int i = 1; i <= numOfDIYClassPost; i++) {
            classPostRepository.save(
                    ClassPost.builder()
                            .user(user)
                            .title("diy" + i)
                            .content("content")
                            .category(Category.DIY)
                            .build());
        }
        int numOfTravelClassPost = 3;
        for (int i = 1; i <= numOfTravelClassPost; i++) {
            classPostRepository.save(
                    ClassPost.builder()
                            .user(user)
                            .title("travel" + i)
                            .content("content")
                            .category(Category.TRAVEL)
                            .build());
        }

        //when
        int pageSize = 5;
        Page<ClassPostDTO> findPostsOfDIY = classPostStatusService.findAllByCategoryAndPaging(Category.DIY, 0, pageSize);
        Page<ClassPostDTO> findPostsOfTravel = classPostStatusService.findAllByCategoryAndPaging(Category.TRAVEL, 0, pageSize);

        //then
        assertThat(findPostsOfDIY.getTotalElements()).isEqualTo(numOfDIYClassPost);
        assertThat(findPostsOfTravel.getTotalElements()).isEqualTo(numOfTravelClassPost);

        assertThat(findPostsOfDIY.getContent().get(0).getTitle()).isEqualTo("diy10");
        assertThat(findPostsOfDIY.getContent().size()).isEqualTo(pageSize);

    }
}