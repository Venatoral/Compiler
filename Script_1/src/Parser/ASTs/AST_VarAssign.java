package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Type_Obj;

public class AST_VarAssign extends AST {
	private AST_Var var;
	private AST_CalcExp calc_exp;
	private String opt;
	
	public boolean setVar(AST_Var var){
		this.var=var;
		return true;
	}
	public boolean setOpt(String opt){
		this.opt=opt;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp calc_exp){
		this.calc_exp=calc_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {	
		interpreter.interpret(this.var);
		interpreter.interpret(this.calc_exp);
		if(this.opt.equals("=")){
			if(this.calc_exp.data_obj==null){
				this.var.data_obj=this.calc_exp.boxObj();
			}else{
				this.var.data_obj=this.calc_exp.data_obj;
			}
			this.var.data_obj.setInit(true);
			interpreter.getCrtFrm().getCrtEnv().addObj(var.name, var.data_obj);
		}else{
			switch(this.opt){//TODO
			case "+=":
				break;
			case "-=":
				break;
			case "*=":
				break;
			case "/=":
				break;
			default:
				return false;
			}
		}
		return true;
	}

}
