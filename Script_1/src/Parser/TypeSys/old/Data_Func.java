//a function's properties
package Parser.TypeSys.old;

import java.util.ArrayList;

import Interpreter.*;
import Interpreter.old.Interpreter;
import Interpreter.old.RT_CtrFlow;
import Interpreter.old.RT_Env;
import Interpreter.old.RT_Frame;
import Parser.ASTs.old.AST_StmtList;

public class Data_Func {
	private boolean isMethod;		//is obj's method
	private Type_Obj class_type;		//obj as method caller
	private Type_Func type_func;	
	private ArrayList<String> par_list=new ArrayList<String>();
	private AST_StmtList stmt_list;
	
	public Data_Obj run(Interpreter interpreter, ArrayList<Data_Obj> arg_list){
		RT_Frame crt_frm=interpreter.getCrtFrm();
		RT_Frame new_frm=new RT_Frame();
		interpreter.setCrtFrm(new_frm);
		new_frm.setRtnFrm(crt_frm);
		RT_Env new_env=new RT_Env();
		new_frm.setCrtEnv(new_env);
		for(int i=0;i<par_list.size();i++){
			Data_Obj o=new Data_Obj(arg_list.get(i));
			new_env.addObj(par_list.get(i), o);
		}
		
		arg_list.clear();
		interpreter.interpret(stmt_list);
		Data_Obj o=new_frm.getRtnObj();
		interpreter.setCrtFrm(crt_frm);
		interpreter.getCtrFlow().setFlow(RT_CtrFlow.Flow_State.s_go);
		return new Data_Obj(o);
	}
	
	public boolean isMethod() {
		return isMethod;
	}
	public void setMethod(boolean isMethod) {
		this.isMethod = isMethod;
	}
	public Type_Obj getClassType() {
		return class_type;
	}
	public void setClassType(Type_Obj class_type) {
		this.class_type = class_type;
	}
	public Type_Func getTypeFunc() {
		return type_func;
	}
	public void setTypeFunc(Type_Func type_func) {
		this.type_func = type_func;
	}
	public ArrayList<String> getParList() {
		return par_list;
	}
	public void setParList(ArrayList<String> par_list) {
		this.par_list = par_list;
	}
	public AST_StmtList getStmtList() {
		return stmt_list;
	}
	public void setStmtList(AST_StmtList stmt_list) {
		this.stmt_list = stmt_list;
	}
}