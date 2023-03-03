package app.community.domain.member.facade;

import app.community.domain.member.entity.Member;
import app.community.domain.member.service.MemberService;
import app.community.domain.post.service.CommentService;
import app.community.domain.post.service.ContentService;
import app.community.global.test.TestMemberBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberFacadeTest {

    @Mock
    private MemberService memberService;

    @Mock
    private CommentService commentService;

    @Mock
    private ContentService contentService;

    @InjectMocks
    private MemberFacade memberFacade;

    private final String TEST_EMAIL = "test1@testmail.com";

    @Test
    void testDeleteMember() {
        // given
        Member member = new TestMemberBuilder(TEST_EMAIL).build();

        // When
        when(memberService.findMember(member.getId())).thenReturn(member);

        memberFacade.delete(member.getId());

        // Then
        verify(commentService, times(1)).deleteAllComment(anyList());
        verify(contentService, times(1)).deleteAllContent(anyList());
        verify(memberService, times(1)).deleteMember(member.getId());
    }

}