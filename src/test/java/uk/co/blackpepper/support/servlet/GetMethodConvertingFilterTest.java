package uk.co.blackpepper.support.servlet;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetMethodConvertingFilterTest {

	@Test
	public void doFilterInvokesChainWithGetRequest() throws Exception {
		FilterChain chain = mock(FilterChain.class);
		
		new GetMethodConvertingFilter().doFilter(mock(HttpServletRequest.class), null, chain);
		
		ArgumentCaptor<HttpServletRequest> captor = ArgumentCaptor.forClass(HttpServletRequest.class);
		verify(chain).doFilter(captor.capture(), any(HttpServletResponse.class));
		assertThat(captor.getValue().getMethod(), is("GET"));
	}

	@Test
	public void doFilterInvokesChainWithResponse() throws Exception {
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain chain = mock(FilterChain.class);
		
		new GetMethodConvertingFilter().doFilter(mock(HttpServletRequest.class), response, chain);
		
		verify(chain).doFilter(any(HttpServletRequest.class), same(response));
	}
}
