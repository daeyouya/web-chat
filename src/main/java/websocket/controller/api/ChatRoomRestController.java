package websocket.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import websocket.dao.ChatRepository;
import websocket.dto.ChatRoomDto;
import websocket.service.social.PrincipalDetails;

@RestController
@Slf4j
public class ChatRoomRestController {

    // ChatRepository Bean 가져오기
    @Autowired
    private ChatRepository chatRepository;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return

    /*
    // 스프링 시큐리티의 로그인 유저 정보는 Security 세션의 PrincipalDetails 안에 담긴다
    // 정확히는 PrincipalDetails 안에 ChatUser 객체가 담기고, 이것을 가져오면 된다.
    @GetMapping("/")
    public String goChatRoom(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("list", chatRepository.findAllRoom());

        // principalDetails 가 null 이 아니라면 로그인 된 상태!!
        if (principalDetails != null) {
            // 세션에서 로그인 유저 정보를 가져옴
            model.addAttribute("user", principalDetails.getUser());
            log.info("user [{}] ",principalDetails);
        }

//        model.addAttribute("user", "hey");
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());
        return "roomlist";
    }
    */





    // 채팅방 생성
    @Tag(name = "chat", description = "v1-api-chat")
    @Operation(summary = "chat", description = "v1-api-chat 입니다.")
    @PostMapping("/v1/api/chat/createroom")
    public String createRoom(@RequestParam("roomName") @Parameter(name="roomName", description = "채팅방 이름", example = "채팅방1") String roomName,
                             @RequestParam("roomPwd") @Parameter(name="roomPwd", description = "채팅방 비밀번호", example = "1234") String roomPwd,
                             @RequestParam("secretChk") @Parameter(name="secretChk", description = "채팅방 잠금 여부(false, true)", example = "false") String secretChk,
                             @RequestParam(value = "maxUserCnt", defaultValue = "100") @Parameter(name="maxUserCnt", description = "채팅방 최대 인원수(기본값 100)", example = "50") String maxUserCnt,
                             RedirectAttributes rttr) {

//        log.info("chk {}", secretChk);
        // 매개변수 : 방 이름, 패스워드, 방 잠금 여부, 방 인원수
        ChatRoomDto room = chatRepository.createChatRoom(roomName, roomPwd, Boolean.parseBoolean(secretChk), Integer.parseInt(maxUserCnt));

        log.info("CREATE Chat Room [{}]", room);

        rttr.addFlashAttribute("roomName", room);
        return "방 생성 OK (json으로 수정 예정)";
    }



    /*
    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @GetMapping("/chat/room")
    public String roomDetail(Model model, String roomId, @AuthenticationPrincipal PrincipalDetails principalDetails){

        log.info("roomId {}", roomId);

        // principalDetails 가 null 이 아니라면 로그인 된 상태!!
        if (principalDetails != null) {
            // 세션에서 로그인 유저 정보를 가져옴
            model.addAttribute("user", principalDetails.getUser());
        }

        model.addAttribute("room", chatRepository.findRoomById(roomId));
        return "chatroom";
    }

    // 채팅방 비밀번호 확인
    @PostMapping("/chat/confirmPwd/{roomId}")
    @ResponseBody
    public boolean confirmPwd(@PathVariable String roomId, @RequestParam String roomPwd){

        // 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
        // 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면  false
        return chatRepository.confirmPwd(roomId, roomPwd);
    }

    // 채팅방 삭제
    @GetMapping("/chat/delRoom/{roomId}")
    public String delChatRoom(@PathVariable String roomId){

        // roomId 기준으로 chatRoomMap 에서 삭제, 해당 채팅룸 안에 있는 사진 삭제
        chatRepository.delChatRoom(roomId);

        return "redirect:/";
    }

    @GetMapping("/chat/chkUserCnt/{roomId}")
    @ResponseBody
    public boolean chUserCnt(@PathVariable String roomId){

        return chatRepository.chkRoomUserCnt(roomId);
    }
    */

}
