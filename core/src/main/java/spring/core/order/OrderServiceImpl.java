package spring.core.order;

import spring.core.discount.DisCountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DisCountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DisCountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 할인에 대해서는 discountPolicy가 전부 담당 → 단일 책임 원칙 O
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
