package org.notima.businessobjects.ui.vaadin;

import java.util.List;

import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.notima.generic.businessobjects.BusinessPartner;
import org.notima.generic.ifacebusinessobjects.BusinessObjectFactory;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("tenants")
public class TenantsView extends VerticalLayout {

	private static final long serialVersionUID = -402560307623013755L;

	@SuppressWarnings("rawtypes")
	@Reference
	private List<BusinessObjectFactory> bofs;

	@SuppressWarnings("rawtypes")
	private BusinessObjectFactory bof;
	
	@SuppressWarnings("rawtypes")
	public TenantsView() {
		
		add(new H1("Tenants") );
		
		for (BusinessObjectFactory bf : bofs) {
			
			bof = bf;
			showTenants();
			
		}
		
	}

	private void showTenants() {
		if (bof.listTenants()!=null) {
			
			listTenants();
			
		}
	}
	
	@SuppressWarnings("unchecked")
	private void listTenants() {
		VerticalLayout layout = new VerticalLayout();
		List<BusinessPartner<Object>> tenants = bof.listTenants().getBusinessPartner();
		layout.add(
				new TextField(bof.getSystemName())
		);
		for (BusinessPartner<Object> bp : tenants) {
			layout.add(bp.getName());
		}
		add(layout);
	}
	
}
