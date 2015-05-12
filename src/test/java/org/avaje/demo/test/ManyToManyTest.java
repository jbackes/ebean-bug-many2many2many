package org.avaje.demo.test;

import com.avaje.ebean.Ebean;
import org.avaje.demo.test.model.Level1;
import org.avaje.demo.test.model.Level2;
import org.avaje.demo.test.model.Level3;
import org.avaje.demo.test.model.Level4;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ManyToManyTest {
	@Test
	public void test() {
		Ebean.beginTransaction();
		final Level4 i = new Level4("i");
		final Level4 ii = new Level4("ii");
		final Level4 iii = new Level4("iii");

		Ebean.save(i);
		Ebean.save(ii);
		Ebean.save(iii);

		final Level3 a = new Level3("a");
		final Level3 b = new Level3("b");

		Ebean.save(a);
		Ebean.save(b);

		final Level2 one = new Level2("one");
		final Level2 two = new Level2("two");

		Ebean.save(one);
		Ebean.save(two);

		final Level1 x1 = new Level1("x1");
		final Level1 x2 = new Level1("x2");
		final Level1 x3 = new Level1("x3");
		final Level1 x4 = new Level1("x4");
		final Level1 x5 = new Level1("x5");

		x1.getLevel2s().add(one);
		x2.getLevel2s().add(one);
		x3.getLevel2s().add(two);
		x4.getLevel2s().add(two);
		x5.getLevel2s().add(two);

		x1.getLevel4s().add(i);
		x1.getLevel4s().add(ii);
		x2.getLevel4s().add(ii);
		x2.getLevel4s().add(iii);

		Ebean.save(x1);
		Ebean.save(x2);
		Ebean.save(x3);
		Ebean.save(x4);
		Ebean.save(x5);

		Ebean.commitTransaction();

		final List<Level1> things = Ebean.find(Level1.class).fetch("level4s").fetch("level2s").fetch("level2s.level3s").findList();
		for(final Level1 curThing : things) {
			Assert.assertEquals(1, curThing.getLevel2s().size());
		}

	}
}
