package model.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupMember {
    private List<User> members;

    public GroupMember() {
        this.members = new ArrayList<>();
    }

    // 멤버 리스트에 사용자 추가
    public void addMember(User user) {
        members.add(user);
    }

    // 특정 사용자 제거
    public void removeMember(User user) {
        members.remove(user);
    }

    // 모든 멤버 반환
    public List<User> getMembers() {
        return members;
    }

    // 멤버 수 반환
    public int getMemberCount() {
        return members.size();
    }
}