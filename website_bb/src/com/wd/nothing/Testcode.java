package com.wd.nothing;

import com.wd.member.MemberDTO;

public class Testcode {
	public static void main(String[] args) {
		MemberDTO dto = new MemberDTO();
		dto.setName("name");
		System.out.println(dto.getName());
		test(dto);
		System.out.println(dto.getName());

	}
	public static void test(MemberDTO dto) {
		System.out.println("test function : "+dto.getName());
		dto.setName("hello world!!");
		System.out.println("test function : "+dto.getName());
	}
}
