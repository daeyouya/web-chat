package websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import websocket.Entity.ChatUser;

public interface MemberRepository extends JpaRepository<ChatUser, Long> {

}
