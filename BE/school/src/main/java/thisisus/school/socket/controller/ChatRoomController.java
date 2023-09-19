//package thisisus.school.socket.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
////import thisisus.school.socket.ChatRoomRepository;
//import thisisus.school.socket.model.ChatRoom;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/chat")
//public class ChatRoomController {
//
//    // 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    public ResponseEntity<List<ChatRoom>> rooms() {
//        return ResponseEntity.ok(chatRoomRepository.findAllRoom());
//    }
//
//    // 채팅방 생성
//    @PostMapping("/room")
//    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name) {
//        return ResponseEntity.ok(chatRoomRepository.createChatRoom(name));
//    }
//
//    // 특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<ChatRoom> roomInfo(@PathVariable String roomId) {
//        return ResponseEntity.ok(chatRoomRepository.findRoomById(roomId));
//    }
//}
