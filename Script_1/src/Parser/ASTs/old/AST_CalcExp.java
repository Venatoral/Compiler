package Parser.ASTs.old;

import Interpreter.old.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Base;
import Parser.TypeSys.old.Type_Obj;

public class AST_CalcExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StrExp str_exp;	
	Data_Obj data_obj;
	public boolean setCalcExp(AST_BoolExp bool_exp, AST_StrExp str_exp){
		this.bool_exp=bool_exp;
		this.str_exp=str_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.bool_exp!=null){
			interpreter.interpret(this.bool_exp);			
			if(this.bool_exp.data_obj!=null){
				this.data_obj=new Data_Obj(this.bool_exp.data_obj);
				return true;
			}else
				System.out.println("error CalcExp eval BoolExp with null value");
				return false;
		}else if(str_exp!=null){
			//TODO 
			return true;
		}
		System.out.println("CalcExp eval error of null nodes");
		return false;
	}
}