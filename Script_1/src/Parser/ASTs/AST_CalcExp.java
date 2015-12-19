package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;
import Parser.TypeSys.Type_Obj;

public class AST_CalcExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StrExp str_exp;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
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
			if(this.bool_exp.base_type!=null){
				this.base_type=this.bool_exp.base_type;
				Type_Obj t = null;
				switch(bool_exp.base_type){
				case t_int:
					this.int_value=bool_exp.int_value;
					t=interpreter.getGlbEnv().getType("int");
					break;
				case t_double:
					this.double_value=bool_exp.double_value;
					t=interpreter.getGlbEnv().getType("double");
					break;
				case t_bool:
					this.bool_value=bool_exp.bool_value;
					t=interpreter.getGlbEnv().getType("bool");
					break;
				case t_char:
					this.char_value=bool_exp.char_value;
					t=interpreter.getGlbEnv().getType("char");
					break;
				case t_string:
					this.string_value=bool_exp.string_value;
					t=interpreter.getGlbEnv().getType("string");
					break;
				default:
					break;	
				}
				this.data_obj=boxObj();
				this.data_obj.setTypeObj(t);
			}else if(this.bool_exp.data_obj!=null){
				this.data_obj=new Data_Obj(this.bool_exp.data_obj);
			}else
				return false;
		}else if(str_exp!=null){
			//TODO
		}
		return false;
	}
	public Data_Obj boxObj(){
		Data_Obj obj=new Data_Obj();
		switch(this.base_type){
		case t_int:
			obj.setIntV(this.int_value);
			break;
		case t_double:
			obj.setDoubleV(this.double_value);
			break;
		case t_bool:
			obj.setBoolV(this.bool_value);
			break;
		case t_char:
			obj.setCharV(this.char_value);
			break;
		case t_string:
			obj.setStringV(this.string_value);
			break;
		default:
			break;	
		}
		return obj;
	}

}
