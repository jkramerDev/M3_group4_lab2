package tree;

import java.util.ArrayList;
import java.util.List;

 
public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private GenericTreeNode<Employee> root;

	public void addRoot(Employee e) {
		root = new GenericTreeNode<>(e);
	}
	@Override
	public void clear() {
		root = null;
	}
	
	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		GenericTreeNode<Employee> managerNode = findEmployeeNode(root,manager);
		if(managerNode != null) {
			managerNode.addChild(new GenericTreeNode<>(newPerson));
		}
	}
	@Override
	public void removeEmployee(Employee firedPerson) {
		GenericTreeNode<Employee> employeeNode = findEmployeeNode(root, firedPerson);
		if(employeeNode != null) {
			GenericTreeNode<Employee> parentNode = findParentNode(root, firedPerson);
			if(parentNode != null){
				parentNode.getChildren().addAll(employeeNode.getChildren());
				parentNode.getChildren().remove(employeeNode);
			}
		}
	}
	@Override
	public void showOrgChartDepthFirst() {
		depthFirstTraversal(root);
	}
	@Override 
	public void showOrgChartBreadthFirst() {
		breadthFirstTraversal(root);
	}
	
	private void depthFirstTraversal(GenericTreeNode<Employee> node) {
		if(node == null)
			return;
		System.out.println(node.getData().getName());
		for(GenericTreeNode<Employee> child : node.getChildren()) {
			depthFirstTraversal(child);
		}
	}
	private void breadthFirstTraversal(GenericTreeNode<Employee> root) {
		if(root == null)
			return;
		Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			GenericTreeNode<Employee> node = queue.poll();
			System.out.println(node.getData().getName());
			for(GenericTreeNode<Employee> child : node.getChildren()) {
				queue.add(child);
			}
		}
	}
	private GenericTreeNode<Employee> findEmployeeNode(GenericTreeNode<Employee> node, Employee employee){
		if(node == null)
			return null;
		if(node.getData().equals(employee))
			return node;
		for(GenericTreeNode<Employee> child : node.getChildren()) {
			GenericTreeNode<Employee> result = findEmployeeNode(child, employee);
			if(result != null)
				return result;
		}
		return null;
	}
	private GenericTreeNode<Employee> findParentNode(GenericTreeNode<Employee> node, Employee employee){
		if(node == null||  node.getChildren().isEmpty())
			return null;
		for(GenericTreeNode<Employee> child : node.getChildren()) {
			if(child.getData().equals(employee)) {
				return node;
			}else {
				GenericTreeNode<Employee> result = findParentNode(child,employee);
				if(result != null )
					return result;
			}
		}
		return null;
				
	}
}

	
}
