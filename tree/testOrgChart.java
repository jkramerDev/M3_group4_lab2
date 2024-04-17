package tree;

public class testOrgChart {

	public static void main(String[] args) {
OrgChartImpl orgChart = new OrgChartImpl();
		
		// fill the org chart
		Employee ceo = new Employee();
		ceo.setName("Paul Simon");
		ceo.setId(1);
		ceo.setPosition("CEO");
		orgChart.addRoot(ceo);
		
		Employee manager1 = new Employee();
		manager1.setName("Dedra Sams");
		manager1.setId(2);
		manager1.setPosition("Manager");
		orgChart.addDirectReport(ceo, manager1);
		
		Employee manager2 = new Employee();
		manager2.setName("Judy Hannan");
		manager2.setId(3);
		manager2.setPosition("Manager");
		orgChart.addDirectReport(ceo, manager2);
		
		Employee employee1 = new Employee();
		employee1.setName("Gordon Smith");
		employee1.setId(4);
		employee1.setPosition("Employee");
		orgChart.addDirectReport(manager1, employee1);	
		
		Employee employee2 = new Employee();
		employee2.setName("Gavin Bicho");
		employee2.setId(4);
		employee2.setPosition("Employee");
		orgChart.addDirectReport(manager2, employee2);
		
	
		// show it depth first
		System.out.println("OrgChart Depth First: ");
		orgChart.showOrgChartDepthFirst();
		System.out.println();
		
		// show breadth first
		System.out.println("OrgChart Breadth First: ");
		orgChart.showOrgChartBreadthFirst();
		System.out.println();
		
		// and remove some people
		orgChart.removeEmployee(employee1);
		
		// show it depth first
		System.out.println("OrgChart Depth First after Removal: ");
		orgChart.showOrgChartDepthFirst();
		System.out.println();
		
		// show breadth first
		System.out.println("OrgChart Breadth First after Removal: ");
		orgChart.showOrgChartBreadthFirst();
		System.out.println();


	}

}
