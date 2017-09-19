package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.MemberDao;
import only.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao md;

	@Override
	public Member getMemberById(String member_id) {
		return md.getMemberById(member_id);
	}

	@Override
	public List<Member> searchMember(String searchTerm) {
		return md.searchMember(searchTerm);
	}
}