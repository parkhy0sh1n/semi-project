package com.gdu.vinery.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdu.vinery.domain.SleepUserDTO;
import com.gdu.vinery.mapper.UserMapper;

@Component
public class SleepUserCheckInterceptor implements HandlerInterceptor {
	
		// 로그인 이전에
		// 휴면 회원인지 확인해서 휴면해제 화면으로 이동시키는 인터셉터
		
		@Autowired
		private UserMapper userMapper;
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			
			// 로그인을 위해서 사용자가 입력한 아이디
			String userId = request.getParameter("userId");
			
			// 해당 아이디가 휴면 테이블에 있는지 확인
			SleepUserDTO sleepUserDTO = userMapper.selectSleepUserById(userId);
			
			// 휴면 테이블에 정보가 있다면 휴면해제화면(/user/wakeup.form)으로 이동
			if(sleepUserDTO != null) {
				
				// session에 sleepUserId를 올려 놓으면 wakeup.jsp에서 휴면회원의 아이디를 확인할 수 있다.
				HttpSession session = request.getSession();
				session.setAttribute("sleepUserId", userId);
				
				// 아직 미구현 확인하고 처리하기!
				// 아직 미구현 확인하고 처리하기!
				// 아직 미구현 확인하고 처리하기!
				// 아직 미구현 확인하고 처리하기!
				response.sendRedirect(request.getContextPath() + "/user/wakeup.form");
				return false;	// 컨트롤 실행 안하다.
			}
			
			return true;	// 컨트롤 실행!
		}

}
