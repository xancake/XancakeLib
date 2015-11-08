package de.xancake.ui.mvc.viewservice;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import de.xancake.ui.mvc.ControllerListener;
import de.xancake.ui.mvc.Controller;
import de.xancake.ui.mvc.ViewListener;
import de.xancake.ui.mvc.View_A;
import de.xancake.ui.mvc.View;

@RunWith(JUnit4.class)
public class ViewServiceTest {
	private ViewService myViewService;
	
	@Before
	public void setUp() {
		ViewFactory.getInstance().setViewService(new DefaultViewService());
		myViewService =  ViewFactory.getInstance().getViewService();
	}
	
	@After
	public void tearDown() {
		myViewService.reset();
	}
	
	@Test
	public void testIsControllerRegistered() {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		myViewService.registerView(DoubleControllerDummy.class, DoubleViewDummy.class);
		
		assertTrue(myViewService.isControllerRegistered(StringControllerDummy.class));
		assertFalse(myViewService.isControllerRegistered(IntegerControllerDummy.class));
		assertTrue(myViewService.isControllerRegistered(DoubleControllerDummy.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_Null() {
		myViewService.registerView(GenericControllerDummy.class, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassIsAbstract() {
		myViewService.registerView(GenericControllerDummy.class, AbstractViewDummy.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassIsInterface() {
		myViewService.registerView(InterfaceControllerDummy.class, InterfaceViewDummy.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassNoParameterFreeConstructor() {
		myViewService.registerView(GenericControllerDummy.class, NoParameterFreeConstructorViewDummy.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassProtectedConstructor() {
		myViewService.registerView(GenericControllerDummy.class, ProtectedConstructorViewDummy.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassPackagePrivateConstructor() {
		myViewService.registerView(GenericControllerDummy.class, PackagePrivateConstructorViewDummy.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterView_IllegalArgument_ViewClassPrivateConstructor() {
		myViewService.registerView(GenericControllerDummy.class, PrivateConstructorViewDummy.class);
	}
	
	@Test
	public void testCreateView() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		myViewService.registerView(IntegerControllerDummy.class, IntegerViewDummy.class);
		myViewService.registerView(DoubleControllerDummy.class, DoubleViewDummy.class);
		
		StringViewDummy stringView = myViewService.createView(StringControllerDummy.class);
		IntegerViewDummy integerView = myViewService.createView(IntegerControllerDummy.class);
		DoubleViewDummy doubleView = myViewService.createView(DoubleControllerDummy.class);
		
		assertNotNull(stringView);
		assertNotNull(integerView);
		assertNotNull(doubleView);
		
		assertNotSame(stringView, integerView);
		assertNotSame(stringView, doubleView);
		assertNotSame(integerView, doubleView);
	}
	
	@Test
	public void testCreateView_MultipleSameControllers() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		
		StringViewDummy stringView1 = myViewService.createView(StringControllerDummy.class);
		StringViewDummy stringView2 = myViewService.createView(StringControllerDummy.class);
		StringViewDummy stringView3 = myViewService.createView(StringControllerDummy.class);
		
		assertNotNull(stringView1);
		assertNotNull(stringView2);
		assertNotNull(stringView3);
		
		assertNotSame(stringView1, stringView2);
		assertNotSame(stringView1, stringView3);
		assertNotSame(stringView2, stringView3);
	}
	
	@Test
	public void testCreateView_ViewNotRegistered() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		myViewService.registerView(DoubleControllerDummy.class, DoubleViewDummy.class);
		
		StringViewDummy stringView = myViewService.createView(StringControllerDummy.class);
		IntegerViewDummy integerView = myViewService.createView(IntegerControllerDummy.class);
		DoubleViewDummy doubleView = myViewService.createView(DoubleControllerDummy.class);
		
		assertNotNull(stringView);
		assertNull(integerView);
		assertNotNull(doubleView);
		
		assertNotSame(stringView, doubleView);
	}
	
	@Test
	public void testCreateView_ControllerViewFetching() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		
		StringControllerDummy stringController = new StringControllerDummy();
		assertNotNull(stringController.getView());
		
		StringViewDummy stringView1 = myViewService.createView(stringController);
		StringViewDummy stringView2 = myViewService.createView(stringController);
		assertNotNull(stringView1);
		assertNotNull(stringView2);
		
		assertNotEquals(stringController.getView(), stringView1);
		assertNotEquals(stringController.getView(), stringView2);
		assertNotEquals(stringView1, stringView2);
	}
	
	@Test
	public void testCreateView_ControllerViewFetching_MultipleSameControllers() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		
		StringControllerDummy stringController1 = new StringControllerDummy();
		StringControllerDummy stringController2 = new StringControllerDummy();
		StringControllerDummy stringController3 = new StringControllerDummy();
		
		assertNotNull(stringController1.getView());
		assertNotNull(stringController2.getView());
		assertNotNull(stringController3.getView());
		
		assertNotEquals(stringController1.getView(), stringController2.getView());
		assertNotEquals(stringController1.getView(), stringController3.getView());
		assertNotEquals(stringController2.getView(), stringController3.getView());
	}
	
	@Test
	public void testCreateView_ControllerViewFetching_ViewNotRegistered() throws Exception {
		myViewService.registerView(StringControllerDummy.class, StringViewDummy.class);
		myViewService.registerView(DoubleControllerDummy.class, DoubleViewDummy.class);
		
		StringControllerDummy stringController = new StringControllerDummy();
		IntegerControllerDummy interfaceController = new IntegerControllerDummy();
		DoubleControllerDummy doubleController = new DoubleControllerDummy();
		
		assertNotNull(stringController.getView());
		assertNull(interfaceController.getView());
		assertNotNull(doubleController.getView());
		
		assertNotEquals(stringController.getView(), doubleController.getView());
	}
	
	private abstract class ViewDummy<M> extends View_A<M, ViewListener> {
		@Override
		public void fillViewWithModel(M model) {}
		
		@Override
		public String toString() { return getClass().getSimpleName(); }
	}
	
	private abstract class ControllerDummy<M, V extends View<M, ViewListener>> extends Controller<M, ViewListener, V, ControllerListener> {
		public ControllerDummy() {
			super(null, null);
		}
		
		@Override
		protected ViewListener getViewListener() { return null; }
	}
	
	public class StringViewDummy extends ViewDummy<String> {}
	public class IntegerViewDummy extends ViewDummy<Integer> {}
	public class DoubleViewDummy extends ViewDummy<Double> {}
	
	public abstract class AbstractViewDummy extends ViewDummy<Object> {}
	public interface InterfaceViewDummy extends View<Object, ViewListener> {}
	public class NoParameterFreeConstructorViewDummy extends ViewDummy<Object> {
		public NoParameterFreeConstructorViewDummy(String string) {}
		public NoParameterFreeConstructorViewDummy(Integer integer) {}
	}
	public class ProtectedConstructorViewDummy extends ViewDummy<Object> {
		protected ProtectedConstructorViewDummy() {}
	}
	public class PackagePrivateConstructorViewDummy extends ViewDummy<Object> {
		PackagePrivateConstructorViewDummy() {}
	}
	public class PrivateConstructorViewDummy extends ViewDummy<Object> {
		private PrivateConstructorViewDummy() {}
	}
	
	public class StringControllerDummy extends ControllerDummy<String, StringViewDummy> {}
	public class IntegerControllerDummy extends ControllerDummy<Integer, IntegerViewDummy> {}
	public class DoubleControllerDummy extends ControllerDummy<Double, DoubleViewDummy> {}
	public class GenericControllerDummy extends ControllerDummy<Object, ViewDummy<Object>> {}
	public class InterfaceControllerDummy extends ControllerDummy<Object, View<Object, ViewListener>> {}
}
