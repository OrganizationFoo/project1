package asof191216;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import com.google.common.collect.Lists;

public class Solution {
	private static final String AMOUNT = "amount";
	private static final String PERCENTAGE = "percentage";

	public static void main(String[] args) {
		JUnitCore.main("Solution");
	}

	class PaymentAccount {
		final String name;
		final BigDecimal limit;
		BigDecimal amountCredited;

		public PaymentAccount(String name, BigDecimal limit) {
			this.name = name;
			this.limit = limit;
		}

		public BigDecimal getLimit() {
			return limit;
		}

		public void setCredit(BigDecimal amountCredited) {
			this.amountCredited = amountCredited;
		}

		public BigDecimal getAmountCredited() {
			return amountCredited;
		}
	}

	class PaymentSplitter {
		List<PaymentAccount> accounts;
		String type;

		public PaymentSplitter(List<PaymentAccount> accounts, String type) {
			this.accounts = accounts;
			this.type = type;
		}

		public void split(BigDecimal totalAmount) {
			// ..
		}
	}

	@Test
	public void testSplitByAmount400() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(500.0));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(1500.0));
		PaymentAccount accountThree = new PaymentAccount("Account 3", null);

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				AMOUNT);
		splitter.split(BigDecimal.valueOf(400.0));

		Assert.assertEquals(BigDecimal.valueOf(400.0), accountOne.getAmountCredited());
		Assert.assertEquals(null, accountTwo.getAmountCredited());
		Assert.assertEquals(null, accountThree.getAmountCredited());
	}

	@Test
	public void testSplitByAmount1000() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(500.0));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(1500.0));
		PaymentAccount accountThree = new PaymentAccount("Account 3", null);

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				AMOUNT);
		splitter.split(BigDecimal.valueOf(1000.00));

		Assert.assertEquals(BigDecimal.valueOf(500.0), accountOne.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(500.0), accountTwo.getAmountCredited());
		Assert.assertEquals(null, accountThree.getAmountCredited());
	}

	@Test
	public void testSplitByAmount3200() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(500.0));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(1500.0));
		PaymentAccount accountThree = new PaymentAccount("Account 3", null);

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				AMOUNT);
		splitter.split(BigDecimal.valueOf(3200.00));

		Assert.assertEquals(BigDecimal.valueOf(500.0), accountOne.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(1500.0), accountTwo.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(1200.0), accountThree.getAmountCredited());
	}

	@Test
	public void testSplitByPercentage() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(.2000));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(.4051));
		PaymentAccount accountThree = new PaymentAccount("Account 3", BigDecimal.valueOf(.3949));

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				PERCENTAGE);
		splitter.split(BigDecimal.valueOf(100));

		Assert.assertEquals(BigDecimal.valueOf(20.0).setScale(2, RoundingMode.HALF_EVEN),
				accountOne.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(40.51).setScale(2, RoundingMode.HALF_EVEN),
				accountTwo.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(39.49).setScale(2, RoundingMode.HALF_EVEN),
				accountThree.getAmountCredited());
	}

	@Test
	public void testSplitWithLeftoverCentsAddedToLastAccount() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(.2000));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(.4051));
		PaymentAccount accountThree = new PaymentAccount("Account 3", BigDecimal.valueOf(.3949));

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				PERCENTAGE);
		splitter.split(BigDecimal.valueOf(801));

		Assert.assertEquals(BigDecimal.valueOf(160.20).setScale(2, RoundingMode.HALF_EVEN),
				accountOne.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(324.48).setScale(2, RoundingMode.HALF_EVEN),
				accountTwo.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(316.32).setScale(2, RoundingMode.HALF_EVEN),
				accountThree.getAmountCredited());
	}

	@Test
	public void testSplitWithLeftoverCentsAddedToLastAccount2() {
		PaymentAccount accountOne = new PaymentAccount("Account 1", BigDecimal.valueOf(.200));
		PaymentAccount accountTwo = new PaymentAccount("Account 2", BigDecimal.valueOf(.405));
		PaymentAccount accountThree = new PaymentAccount("Account 3", BigDecimal.valueOf(.395));

		PaymentSplitter splitter = new PaymentSplitter(Lists.newArrayList(accountOne, accountTwo, accountThree),
				PERCENTAGE);
		splitter.split(BigDecimal.valueOf(620.97));

		Assert.assertEquals(BigDecimal.valueOf(124.19).setScale(2, RoundingMode.HALF_EVEN),
				accountOne.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(251.49).setScale(2, RoundingMode.HALF_EVEN),
				accountTwo.getAmountCredited());
		Assert.assertEquals(BigDecimal.valueOf(245.29).setScale(2, RoundingMode.HALF_EVEN),
				accountThree.getAmountCredited());
	}
}
