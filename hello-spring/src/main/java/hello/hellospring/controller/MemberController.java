package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberSerice;

    @Autowired
    public MemberController(MemberService memberSerice) {
        this.memberSerice = memberSerice;
    }

    @GetMapping("/members/new")
    public String creatForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("");

        memberSerice.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberSerice.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
