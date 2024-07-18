package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.live.domain.LiveApplyUser;
import com.woowahanrabbits.battle_people.domain.live.domain.Room;
import com.woowahanrabbits.battle_people.domain.live.infrastructure.LiveApplyUserRepository;
import com.woowahanrabbits.battle_people.domain.live.infrastructure.RoomRepository;
import com.woowahanrabbits.battle_people.domain.user.domain.User;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private LiveApplyUserRepository liveApplyUserRepository;

    @Override
    public Room createRoom(String roomId) {
        Room room = Room.builder()
                .roomId(roomId)
                .build();

        return roomRepository.save(room);
    }

    @Override
    public LiveApplyUser addParticipant(String roomId, long id) {
        Room room = roomRepository.findByRoomId(roomId);
        User user = userRepository.findById(id);

        Date currentTime = new Date();

        LiveApplyUser liveApplyUser = LiveApplyUser.builder()
                .room(room)
                .participant(user)
                .inTime(currentTime)
                .build();

        return liveApplyUserRepository.save(liveApplyUser);
    }

    @Override
    public List<LiveApplyUser> getParticipants(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        return liveApplyUserRepository.findByRoom(room);
    }
}
