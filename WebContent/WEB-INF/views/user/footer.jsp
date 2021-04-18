<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- NEWSLETTER -->
<div id="newsletter" class="section">
	<!-- container -->
	<div class="container">
		<!-- row -->
		<div class="row">
			<div class="col-md-12">
				<div class="newsletter">
					<p>
						ĐĂNG KÝ KHÁCH HÀNG <strong>QUEN THUỘC</strong>
					</p>
					<c:url var="url" value="/sub-member.htm"/>
					<form:form action="${url}">
						<input class="input" type="email" name="subEmail" placeholder="Nhập email">
						<button class="newsletter-btn">
							<i class="fa fa-envelope"></i> Đăng ký
						</button>
					</form:form>
					<ul class="newsletter-follow">
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-instagram"></i></a></li>
						<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /row -->
	</div>
	<!-- /container -->
</div>
<!-- /NEWSLETTER -->

<!-- FOOTER -->
<footer id="footer">
	<!-- top footer -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-3 col-xs-6">
					<div class="footer">
						<h3 class="footer-title">Liên hệ</h3>
						<p>Tòa nhà cao tầng màu vàng Thegioididong ngụ tại địa chỉ</p>
						<ul class="footer-links">
							<li><a href="#"><i class="fa fa-map-marker"></i>182 Lã
									Xuân Oai</a></li>
							<li><a href="#"><i class="fa fa-phone"></i>1900000001</a></li>
							<li><a href="#"><i class="fa fa-envelope-o"></i>didong@gmail.com</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-xs-6">
					<div class="footer">
						<h3 class="footer-title">Mặt hàng</h3>
						<ul class="footer-links">
							<li><a href="#">Mới nhất</a></li>
							<li><a href="#">Đang giảm giá</a></li>
							<li><a href="#">Hệ điều hành IOS</a></li>
							<li><a href="#">Hệ điều hành Android</a></li>
						</ul>
					</div>
				</div>

				<div class="clearfix visible-xs"></div>

				<div class="col-md-3 col-xs-6">
					<div class="footer">
						<h3 class="footer-title">Thông tin</h3>
						<ul class="footer-links">
							<li><a href="#">Về chúng tôi</a></li>
							<li><a href="#">Liên hệ</a></li>
							<li><a href="#">Dịch vụ bảo mật</a></li>
							<li><a href="#">Dịch vụ đổi trả</a></li>
							<li><a href="#">Các điều khoản</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-xs-6">
					<div class="footer">
						<h3 class="footer-title">Dịch vụ</h3>
						<ul class="footer-links">
							<li><a href="#">Tài khoản</a></li>
							<li><a href="#">Giỏ hàng</a></li>
							<li><a href="#">Yêu thích</a></li>
							<li><a href="#">Giúp đỡ</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /top footer -->

	<!-- bottom footer -->
	<div id="bottom-footer" class="section">
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="footer-payments">
						<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
						<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
						<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
						<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
						<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
						<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
					</ul>
					<span class="copyright"> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
						class="fa fa-heart-o" aria-hidden="true"></i> by <a
						href="https://www.facebook.com/" target="_blank">Trường Quỳnh</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</span>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /bottom footer -->
</footer>
<!-- /FOOTER -->