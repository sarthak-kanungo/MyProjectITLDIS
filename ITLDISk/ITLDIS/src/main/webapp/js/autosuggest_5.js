/* Auto-suggest/auto-complete control
 *
 * original code:
 * (C) 2004-2005 zichun
 *
 * fixes, modifications and support:
 * (C) 2007-2009 Dmitriy Khudorozhkov (dmitrykhudorozhkov@yahoo.com) and contributors.
 *
 * This software is provided "as-is", without any express or implied warranty.
 * In no event will the author be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

var suggesturl = ""; // Global link to the server-side script, that gives you the suggestion list.
                     // Used for controls that do not define their own server script urls.
// pre-load images:
var image = new Array(4);

image[0] = new Image(), image[1] = new Image(),
image[2] = new Image(), image[3] = new Image();

image[0].src = "arrow-down.gif", image[1].src = "arrow-down-d.gif";
image[2].src = "arrow-up.gif",   image[3].src = "arrow-up-d.gif";
var img_obj=null;
var text_id="";
var row="";
function actd5(id, ca, url,img,rowId)
{
    img_obj=img;
    text_id=id;
    row=rowId;
	// Public Variables:
        this.actd5_suggesturl  = url || (ca ? "" : suggesturl);

	this.actd5_timeOut     = -1;			// autocomplete Timeout in ms (-1: autocomplete never time out)
	this.actd5_response    = 500;		// time, in milliseconds, between the last char typed and the actual query
	this.actd5_lim         = 10;			// number of elements autocomplete will show

	this.actd5_firstText   = false;		// should the auto complete be limited to the beginning of keyword?
	this.actd5_firstMatch  = true;		// if previous is false, should the exact matches be displayed first?
	this.actd5_fullRefresh = false;		// should the script re-send the AJAX request after each entered character?

	this.actd5_useIFrame   = true;		// should the control use an IFrame element to fix suggestion list positioning (MS IE only)?
	this.actd5_useScroll   = true;		// should the control use a scroll bar (true) or a up/down buttons (false)?
	this.actd5_mouse       = true;		// enable mouse support
	this.actd5_noDefault   = true;		// should the control omit selecting the 1st item in a suggestion list?
	this.actd5_startcheck  = 0;			// show widget only after this number of characters is typed in.

	this.actd5_delimiter   = [";", ","];	// delimiter for multiple autocomplete. Set it to empty array ( [] ) for single autocomplete
	this.ajax_delimiter   = "|"; 		// character that delimits entries in the string returned by AJAX call
	this.item_delimiter   = ","; 		// character that delimits key and value for the suggestion item in the string returned by AJAX call

	this.actd5_selectedIndex = -1;		// index (zero-based) of the element last chosen

	// Styles:
	this.actd5_arColor    = "#656291";  // background color for the "arrows"
	this.actd5_bgColor    = "#FFFFF0";
	this.actd5_textColor  = "#000";
	this.actd5_htextColor = "#F00";
	this.actd5_hColor     = "#D6D7E7";
	this.actd5_fFamily    = "verdana,arial,helvetica";
	this.actd5_arrowSize  = "7px";
	this.actd5_fSize      = "10px";

	// "Private" Variables:
	this.actd5_delimwords = [];
	this.actd5_cdelimword = 0;
	this.actd5_delimchar  = [];
	this.actd5_display    = false;

	this.actd5_pos    = 0;
	this.actd5_total  = 0;
	this.actd5_rangeu = 0;
	this.actd5_ranged = 0;
	this.actd5_bool   = [];
	this.actd5_pre    = 0;
	this.actd5_toid   = 0;
	this.actd5_tomake = false;

	this.cur_x = 0;
	this.cur_y = 0;
	this.cur_w = 0;
	this.cur_h = 0;

	this.actd5_mouse_on_list = 0;
	this.actd5_caretmove     = false;

	this.actd5_base_id  = id;
	this.actd5_curr     = document.getElementById(id);
	this.actd5_prevterm = this.actd5_curr.value;

	this.actd5_keywords = [];
	this.actd5_values   = [];

	this.actd5_keywords_init = [];
	this.actd5_values_init   = [];

	ca = ca || [];
	for(var i = 0, cl = ca.length; i < cl; i++)
	{
		if(String(typeof(ca[i])).toLowerCase() == "string")
		{
			this.actd5_keywords[i] = this.actd5_keywords_init[i] = ca[i];
			this.actd5_values[i]   = this.actd5_values_init[i]   = "";
		}
		else
		{
			this.actd5_keywords[i] = this.actd5_keywords_init[i] = ca[i][0];
			this.actd5_values[i]   = this.actd5_values_init[i]   = ca[i][1];
		}
	}

	return this.construct();
};

actd5.prototype = {

	callLater: function(func, obj)
	{ return function() { func.call(obj) }; },

	construct: function()
	{
		this.actd5_curr.actd5 = this;

		// pre-create event functions
		this.funcClick = this.actd5_mouseclick;
		this.funcCheck = this.actd5_checkkey;

		this.funcHighlight = this.actd5_table_highlight;

		this.funcClear = this.callLater(this.actd5_clear,    this);
		this.funcPress = this.callLater(this.actd5_keypress, this);

		this.funcUp   = this.callLater(this.actd5_goup,   this);
		this.funcDown = this.callLater(this.actd5_godown, this);

		this.funcFocus   = this.callLater(this.actd5_table_focus,   this);
		this.funcUnfocus = this.callLater(this.actd5_table_unfocus, this);

		addEvent(this.actd5_curr, "focus", this.callLater(this.actd5_setup, this));

		return this;
	},

	actd5_setup: function()
	{
		addEvent(document,       "keydown",  this.funcCheck);
		addEvent(this.actd5_curr, "blur",     this.funcClear);
		addEvent(document,       "keypress", this.funcPress);
	},

	actd5_clear: function()
	{
		var msie  = (document.all && !window.opera) ? true : false;
		var event = window.event;

		if(msie && event && this.cur_h)
		{
			var x = event.clientX, y = event.clientY;

			y += document.body.scrollTop;
			x += document.body.scrollLeft;

			if(((x > this.cur_x) && (x < (this.cur_x + this.cur_w))) && ((y > this.cur_y) && (y < (this.cur_y + this.cur_h))))
			{
				this.actd5_curr.focus();
				return;
			}
		}

		removeEvent(document,       "keydown",  this.funcCheck);
		removeEvent(this.actd5_curr, "blur",     this.funcClear);
		removeEvent(document,       "keypress", this.funcPress);

		this.actd5_removedisp();
	},

	actd5_parse: function(n)
	{
		if(!n || !n.length) return n;

	    var t, plen;
		if(this.actd5_delimiter.length > 0)
		{
			   t = this.actd5_delimwords[this.actd5_cdelimword].trim().addslashes();
			plen = this.actd5_delimwords[this.actd5_cdelimword].trim().length;
		}
		else
		{
			   t = this.actd5_curr.value.addslashes();
			plen = this.actd5_curr.value.length;
		}

		if(!plen) return n;

		var tobuild = [];
		var c = 0;

		var re = this.actd5_firstText ? new RegExp("^" + t, "i") : new RegExp(t, "i");
		var p = n.search(re);

		tobuild[c++] = n.substr(0, p);

		tobuild[c++] = "<u><font face='" + this.actd5_fFamily + "'>";

		tobuild[c++] = n.substring(p, plen + p);

		tobuild[c++] = "</font></u>";

		tobuild[c++] = n.substring(plen + p, n.length);

		return tobuild.join("");
	},

	actd5_generate: function()
	{
		var body = document.getElementById("tat_table_" + this.actd5_base_id);

		if(body)
		{
			this.actd5_display = false;
			document.body.removeChild(body);

			var helper = document.getElementById("tat_helper_" + this.actd5_base_id);
			if(helper)
				document.body.removeChild(helper);
		}

		if(this.actd5_total == 0)
		{
			this.actd5_display = false;
			return;
		}

		var msie = (document.all && !window.opera) ? true : false;

		var bb = document.createElement("div");
		bb.id  = "tat_table_" + this.actd5_base_id;
		bb.style.position = "absolute";
		bb.style.border = "#000000 solid 1px";
		bb.style.zIndex = 100;

		this.cur_y = curTop(this.actd5_curr) + this.actd5_curr.offsetHeight;
		bb.style.top = this.cur_y + "px";

		this.cur_x = bb.style.left = curLeft(this.actd5_curr);
		bb.style.left = this.cur_x + "px";

		this.cur_w = this.actd5_curr.offsetWidth - (msie ? 0 : 2);
		bb.style.width = this.cur_w + "px";

		this.cur_h = 1;
		bb.style.height = "1px"

		var cc = null;
		if(msie && this.actd5_useIFrame)
		{
			var cc = document.createElement("iframe");
			cc.id  = "tat_helper_" + this.actd5_base_id;

			cc.src = "javascript:'<html></html>';";
			cc.scrolling = "no";
			cc.frameBorder = "no";

			cc.style.display = "block";
			cc.style.position = "absolute";

			cc.style.zIndex = 99;
			cc.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=0)";
		}

		var actd5_str = [];
		var cn = 0;

		actd5_str[cn++] = "<table cellspacing='1px' cellpadding='2px' style='width:100%;background-color:" + this.actd5_bgColor + "' id='tat_table2_" + this.actd5_base_id + "'>";

		if(this.actd5_useScroll && (this.actd5_total > this.actd5_lim))
		{
			this.cur_h = this.actd5_lim * parseInt(this.actd5_fSize);
			bb.style.height = this.cur_h + "px";

			bb.style.overflow  = "auto";
			bb.style.overflowX = "hidden";
		}

		if(cc)
		{
			document.body.appendChild(cc);

			cc.style.top  = this.cur_y + "px";
			cc.style.left = this.cur_x + "px";

			cc.style.width  = bb.offsetWidth + 2;
		}
		document.body.appendChild(bb);

		var counter = 0, first = true, j = 1;

		for(var i = 0; i < this.actd5_keywords.length; i++)
		{
			if(!this.actd5_useScroll && ((this.actd5_keywords.length > this.actd5_lim) && (this.actd5_total > this.actd5_lim) && !i))
			{
				actd5_str[cn++] = "<tr style='background-color:" + this.actd5_arColor + "'>";
				actd5_str[cn++] = "<td style='color:" + this.actd5_textColor + ";font-family:arial narrow;font-size:" + this.actd5_arrowSize + ";cursor:default" + "' align='center'></td></tr>";
			}

			if(this.actd5_bool[i] && (this.actd5_useScroll || (counter < this.actd5_lim)))
			{
				counter++;

				actd5_str[cn++] = "<tr style='background-color:";

				var tcolor = this.actd5_textColor;

				if((first && !this.actd5_noDefault && !this.actd5_tomake) || (this.actd5_pre == i))
				{
					actd5_str[cn++] = this.actd5_hColor;
					tcolor = this.actd5_htextColor;
					first = false;
				}
				else
				{
					actd5_str[cn++] = this.actd5_bgColor;
				}
				actd5_str[cn++] = "' id='tat_tr_" + this.actd5_base_id + String(j) + "'>";

				actd5_str[cn++] = "<td style='color:" + tcolor + ";font-family:" + this.actd5_fFamily + ";font-size:" + this.actd5_fSize + ";white-space:nowrap" + "'>" + this.actd5_parse(this.actd5_keywords[i]) + "</td></tr>";

				j++;
			}
		}

		if(!this.actd5_useScroll && (this.actd5_total > this.actd5_lim))
		{
			actd5_str[cn++] = "<tr style='background-color:" + this.actd5_arColor + "'>";
			actd5_str[cn++] = "<td style='color:" + this.actd5_textColor + ";font-family:arial narrow;font-size:" + this.actd5_arrowSize + ";cursor:default" + "' align='center'></td></tr>";
		}

		bb.innerHTML = actd5_str.join("");

		var table = bb.firstChild, row_num = table.rows.length, counter = 0, j = 1, real_height = 0, real_width = 0;
		if(this.actd5_mouse)
		{
			table.onmouseout  = this.funcUnfocus;
			table.onmouseover = this.funcFocus;
		}

		for(i = 0; i < row_num; i++)
		{
			var row  = table.rows[i];
			var cell = row.cells[0];

			if(!this.actd5_useScroll && ((this.actd5_keywords.length > this.actd5_lim) && (this.actd5_total > this.actd5_lim) && !i))
			{
				replaceHTML(cell, image[3]);

				// Up arrow:
				real_height += row.offsetHeight + 1;
			}
			else if((i == (row_num - 1)) && (!this.actd5_useScroll && (this.actd5_total > this.actd5_lim)))
			{
				replaceHTML(cell, image[0]);

				// Down arrow:
				addEvent(cell, "click", this.funcDown);

				real_height += row.offsetHeight + 1;
			}
			else
			{
				counter++;

				// Content cells:
				cell.actd5 = this;
				cell.setAttribute("pos", j);

				if(counter <= this.actd5_lim)
					real_height += row.offsetHeight + 1;

				if(real_width < row.offsetWidth)
					real_width = row.offsetWidth;

				if(this.actd5_mouse)
				{
					cell.style.cursor = msie ? "hand" : "pointer";
					addEvent(cell, "click", this.funcClick);
					cell.onmouseover = this.funcHighlight;
				}

				j++;
			}
		}

		real_height += (msie ? 3 : 1);
		this.cur_h = real_height;
		bb.style.height = real_height + "px";

		this.cur_w = (real_width > bb.offsetWidth ? real_width : bb.offsetWidth) + 2;
		bb.style.width  = this.cur_w + "px";

		if(cc)
		{
			this.cur_h = real_height;

			cc.style.height = bb.style.height = this.cur_h + "px";
			cc.style.width  = this.cur_w + "px";
		}

		this.actd5_pos    = this.actd5_noDefault ? 0 : 1;
		this.actd5_rangeu = 1;
		this.actd5_ranged = j - 1;
		this.actd5_display = true;

	},

	actd5_remake: function()
	{
		var msie = (document.all && !window.opera) ? true : false;
		var a = document.getElementById("tat_table2_" + this.actd5_base_id);

		if(this.actd5_mouse)
		{
			a.onmouseout  = this.funcUnfocus;
			a.onmouseover = this.funcFocus;
		}

		var i, k = 0;
		var first = true;
		var j = 1;

		if(this.actd5_total > this.actd5_lim)
		{
		    var b = (this.actd5_rangeu > 1);

			var r = a.rows[k++];
			r.style.backgroundColor = this.actd5_arColor;

			var c = r.firstChild;
			c.style.color = this.actd5_textColor;
			c.style.fontFamily = "arial narrow";
			c.style.fontSize = this.actd5_arrowSize;
			c.style.cursor = "default";
			c.align = "center";

			replaceHTML(c, b ? image[2] : image[3]);

			if(b)
			{
				addEvent(c, "click", this.funcUp);
				c.style.cursor = msie ? "hand" : "pointer";
			}
			else
			{
				c.style.cursor = "default";
			}
		}

		for(var i = 0; i < this.actd5_keywords.length; i++)
		{
			if(this.actd5_bool[i])
			{
				if(j >= this.actd5_rangeu && j <= this.actd5_ranged)
				{
					var r = a.rows[k++];
					r.style.backgroundColor = this.actd5_bgColor;
					r.id = "tat_tr_" + this.actd5_base_id + String(j);

					var c = r.firstChild;
					c.style.color = this.actd5_textColor;
					c.style.fontFamily = this.actd5_fFamily;
					c.style.fontSize = this.actd5_fSize;
					c.innerHTML = this.actd5_parse(this.actd5_keywords[i]);
					c.setAttribute("pos", j);
				}

				j++;
			}

			if(j > this.actd5_ranged) break;
		}

		if(this.actd5_keywords.length > this.actd5_lim)
		{
			var b = ((j - 1) < this.actd5_total);

			var r = a.rows[k];
			r.style.backgroundColor = this.actd5_arColor;

			var c = r.firstChild;
			c.style.color = this.actd5_textColor;
			c.style.fontFamily = "arial narrow";
			c.style.fontSize = this.actd5_arrowSize;
			c.style.cursor = "default";
			c.align = "center";

			replaceHTML(c, b ? image[0] : image[1]);

			if(b)
			{
				addEvent(c, "click", this.funcDown);
				c.style.cursor = msie ? "hand" : "pointer";
			}
			else
			{
				c.style.cursor = "default";
			}
		}

		if((document.all && !window.opera))
		{
			var helper = document.getElementById("tat_helper_" + this.actd5_base_id);
			if(helper)
				helper.style.width  = a.parentNode.offsetWidth + 2;
		}
	},

	actd5_goup: function()
	{
		this.actd5_curr.focus();

		if(!this.actd5_display) return;
		if(this.actd5_pos <= 1) return;

		var t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));
		if(t && t.style)
		{
			t.style.backgroundColor = this.actd5_bgColor;
		    t.firstChild.style.color = this.actd5_textColor;
		}

		this.actd5_pos--;
		t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));

		if(this.actd5_useScroll && t)
		{
			var base = document.getElementById("tat_table_" + this.actd5_base_id);
			base.scrollTop = t.offsetTop;
		}
		else
		{
			if(this.actd5_pos < this.actd5_rangeu)
			{
				this.actd5_rangeu--;
				this.actd5_ranged--;
				this.actd5_remake();
			}

			t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));
		}

		if(t && t.style)
		{
			t.style.backgroundColor = this.actd5_hColor;
		    t.firstChild.style.color = this.actd5_htextColor;
		}

		if(this.actd5_toid)
		{
			clearTimeout(this.actd5_toid);
			this.actd5_toid = 0;
		}

		if(this.actd5_timeOut > 0)
			this.actd5_toid = setTimeout(function() { this.actd5_mouse_on_list = 1; this.actd5_removedisp(); }, this.actd5_timeOut);

		this.actd5_curr.focus();
	},

	actd5_godown: function()
	{
		this.actd5_curr.focus();

		if(!this.actd5_display) return;
		if(this.actd5_pos == this.actd5_total) return;

		if(this.actd5_pos >= 1)
		{
			var t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));
			if(t && t.style)
			{
				t.style.backgroundColor = this.actd5_bgColor;
				t.firstChild.style.color = this.actd5_textColor;
			}
		}
		else this.actd5_pos = 0;

		this.actd5_pos++;
		t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));

		if(this.actd5_useScroll && t)
		{
			var base = document.getElementById("tat_table_" + this.actd5_base_id);
			base.scrollTop = t.offsetTop;
		}
		else
		{
			if(this.actd5_pos > this.actd5_ranged)
			{
				this.actd5_rangeu++;
				this.actd5_ranged++;
				this.actd5_remake();
			}

			t = document.getElementById("tat_tr_" + this.actd5_base_id + String(this.actd5_pos));
		}

		if(t && t.style)
		{
			t.style.backgroundColor = this.actd5_hColor;
			t.firstChild.style.color = this.actd5_htextColor;
		}

		if(this.actd5_toid)
		{
			clearTimeout(this.actd5_toid);
			this.actd5_toid = 0;
		}

		if(this.actd5_timeOut > 0)
			this.actd5_toid = setTimeout(function() { this.actd5_mouse_on_list = 1; this.actd5_removedisp(); }, this.actd5_timeOut);

		this.actd5_curr.focus();
	},

	actd5_mouseclick: function(event)
	{
		var elem = getTargetElement(event);
		if(!elem.id) elem = elem.parentNode;

		var obj = elem.actd5;

		if(!obj)
		{
			var tag = elem.tagName.toLowerCase();
			elem = (tag == "tr") ? elem.firstChild : elem.parentNode;

			obj = elem.actd5;
		}

		if(!obj || !obj.actd5_display) return;

		obj.actd5_mouse_on_list = 0;
		obj.actd5_pos = elem.getAttribute("pos");
		obj.actd5_penter();
                getvinNoData4Install(document.getElementById(text_id));
	},

	actd5_table_focus: function()
	{ this.actd5_mouse_on_list = 1; },

	actd5_table_unfocus: function()
	{
		this.actd5_mouse_on_list = 0;

		if(this.actd5_toid)
		{
			clearTimeout(this.actd5_toid);
			this.actd5_toid = 0;
		}

		if(this.actd5_timeOut > 0)
			this.actd5_toid = setTimeout(function() { obj.actd5_mouse_on_list = 0; this.actd5_removedisp(); }, this.actd5_timeOut);
	},

	actd5_table_highlight: function(event)
	{
		var elem = getTargetElement(event);

		var obj = elem.actd5;
		if(!obj) return;

		obj.actd5_mouse_on_list = 1;

		var row = document.getElementById("tat_tr_" + obj.actd5_base_id + obj.actd5_pos);
		if(row && row.style)
		{
			row.style.backgroundColor = obj.actd5_bgColor;
			row.firstChild.style.color = obj.actd5_textColor;
		}

		obj.actd5_pos = elem.getAttribute("pos");

		row = document.getElementById("tat_tr_" + obj.actd5_base_id + obj.actd5_pos);
		if(row && row.style)
		{
			row.style.backgroundColor = obj.actd5_hColor;
			row.firstChild.style.color = obj.actd5_htextColor;
		}

		if(obj.actd5_toid)
		{
			clearTimeout(obj.actd5_toid);
			obj.actd5_toid = 0;
		}

		if(obj.actd5_timeOut > 0)
			obj.actd5_toid = setTimeout(function() { obj.actd5_mouse_on_list = 0; obj.actd5_removedisp(); }, obj.actd5_timeOut);
	},

 	actd5_penter: function()
	{
		if(!this.actd5_display) return;
		if(this.actd5_pos < 1) return;

		this.actd5_display = false;

		var word = "", c = 0;

		for(var i = 0; i < this.actd5_keywords.length; i++)
		{
			if(this.actd5_bool[i]) c++;

			if(c == this.actd5_pos)
			{
				word = this.actd5_keywords[i];
				break;
			}
		}

		this.actd5_selectedIndex = c;
		this.actd5_insertword(word);
	},

	actd5_insertword: function(a)
	{
		if(this.actd5_delimiter.length > 0)
		{
			var str = "";

			for(var i = 0; i < this.actd5_delimwords.length; i++)
			{
				if(this.actd5_cdelimword == i)
				{
					prespace = postspace = "";
					gotbreak = false;
					for(var j = 0; j < this.actd5_delimwords[i].length; ++j)
					{
						if(this.actd5_delimwords[i].charAt(j) != " ")
						{
							gotbreak = true;
							break;
						}

						prespace += " ";
					}

					for(j = this.actd5_delimwords[i].length - 1; j >= 0; --j)
					{
						if(this.actd5_delimwords[i].charAt(j) != " ") break;
						postspace += " ";
					}

					str += prespace;
					str += a;
					if(gotbreak) str += postspace;
				}
				else
				{
					str += this.actd5_delimwords[i];
				}

				if(i != this.actd5_delimwords.length - 1)
					str += this.actd5_delimchar[i];
			}

			this.actd5_curr.value = str;
			setCaret(this.actd5_curr, this.actd5_curr.value.length);
		}
		else
		{
			this.actd5_curr.value = a;
		}

		this.actd5_mouse_on_list = 0;
		this.actd5_removedisp();
	},

	actd5_removedisp: function()
	{
		if(this.actd5_mouse_on_list == 0)
		{
			this.actd5_display = 0;

			var base = document.getElementById("tat_table_" + this.actd5_base_id);
			if(base)
			{
				var helper = document.getElementById("tat_helper_" + this.actd5_base_id);
				if(helper)
					document.body.removeChild(helper);

				document.body.removeChild(base);
			}

			if(this.actd5_toid)
			{
			  clearTimeout(this.actd5_toid);
			  this.actd5_toid = 0;
			}

			this.cur_x = 0;
			this.cur_y = 0;
			this.cur_w = 0;
			this.cur_h = 0;
		}
	},

	actd5_keypress: function(event)
	{
		if(this.actd5_caretmove) stopEvent(event);
		return !this.actd5_caretmove;
	},

	actd5_checkkey: function(event)
	{
		event = event || window.event;

		var code = event.keyCode;
		var obj = getTargetElement(event).actd5;
		obj.actd5_caretmove = 0;

		var term = "";

		if(obj.actd5_toid)
		{
			clearTimeout(obj.actd5_toid);
			obj.actd5_toid = 0;
		}

		switch(code)
		{
                        // '*' is pressed.
                        case 106:
				if(!obj.actd5_display)
				{
					obj.actd5_toid = setTimeout(function()
					{
						obj.actd5_tocomplete.call(obj, -1);
					},
					25);
				}

				return false;
				break;
                        // Up arrow:
			case 38:
				obj.actd5_goup();
				obj.actd5_caretmove = 1;
				return false;
				break;

			// Down arrow:
			case 40:
				if(!obj.actd5_display)
				{
					obj.actd5_toid = setTimeout(function()
					{
						obj.actd5_tocomplete.call(obj, -1);
					},
					25);
				}
				else
				{
					obj.actd5_godown();
					obj.actd5_caretmove = 1;
				}
				return false;
				break;

			// Page up:
			case 33:
				for(var c = 0; c < obj.actd5_lim; c++)
					obj.actd5_goup();

				obj.actd5_caretmove = 1;
				break;

			// Page down:
			case 34:
				for(var c = 0; c < obj.actd5_lim; c++)
					obj.actd5_godown();

				obj.actd5_caretmove = 1;
				break;

			// Esc:
			case 27:
				term = obj.actd5_curr.value;

				obj.actd5_mouse_on_list = 0;
				obj.actd5_removedisp();
				break;

			// Enter:
			case 13:
				if(obj.actd5_display)
				{
					obj.actd5_caretmove = 1;
					obj.actd5_penter();
					return false;
				}
				break;

			// Tab:
			case 9:
				if((obj.actd5_display && obj.actd5_pos) || obj.actd5_toid)
				{
					obj.actd5_caretmove = 1;
					obj.actd5_penter();

					setTimeout(function() { obj.actd5_curr.focus(); }, 25);
					return false;
				}
				break;

			default:
				break;
		}

		if(term.length) setTimeout(function() { obj.actd5_curr.value = term; }, 25);
		return true;
	},

	actd5_tocomplete: function(kc)
	{
             //alert(img_obj);

		if(this.actd5_toid)
		{
			clearTimeout(this.actd5_toid);
			this.actd5_toid = 0;
		}
		else
		{
			return;
		}

		if(this.actd5_display && (this.actd5_prevterm == this.actd5_curr.value)) return;
		this.actd5_prevterm = this.actd5_curr.value;

		if(kc == 38 || kc == 40 || kc == 13) return;

		if(this.actd5_display)
		{
			var word = 0;
			var c = 0;

			for(var i = 0; i <= this.actd5_keywords.length; i++)
			{
				if(this.actd5_bool[i]) c++;

				if(c == this.actd5_pos)
				{
					word = i;
					break;
				}
			}

			this.actd5_pre = word;
		}
		else
		{


this.actd5_pre = -1;
		}

		if(!this.actd5_curr.value.length && (kc != -1))
		{
			this.actd5_mouse_on_list = 0;
			this.actd5_removedisp();
		}

		var ot, t;

		if(this.actd5_delimiter.length > 0)
		{
                   
			var caret_pos_end = this.actd5_curr.value.length;

			var delim_split = "";
			for(var i = 0; i < this.actd5_delimiter.length; i++)
				delim_split += this.actd5_delimiter[i];

		    delim_split = delim_split.addslashes();
			var delim_split_rx = new RegExp("([" + delim_split + "])");
			c = 0;
			this.actd5_delimwords = [];
			this.actd5_delimwords[0] = "";

			for(var i = 0, j = this.actd5_curr.value.length; i < this.actd5_curr.value.length; i++, j--)
			{
				if(this.actd5_curr.value.substr(i, j).search(delim_split_rx) == 0)
				{
					ma = this.actd5_curr.value.substr(i,j).match(delim_split_rx);
					this.actd5_delimchar[c] = ma[1];
					c++;
					this.actd5_delimwords[c] = "";
				}
				else
				{
					this.actd5_delimwords[c] += this.actd5_curr.value.charAt(i);
				}
			}

			var l = 0;
			this.actd5_cdelimword = -1;
			for(i = 0; i < this.actd5_delimwords.length; i++)
			{
				if((caret_pos_end >= l) && (caret_pos_end <= l + this.actd5_delimwords[i].length))
					this.actd5_cdelimword = i;

				l += this.actd5_delimwords[i].length + 1;
			}

			ot = this.actd5_delimwords[this.actd5_cdelimword].trim();
			 t = this.actd5_delimwords[this.actd5_cdelimword].addslashes().trim();
		}
		else
		{

			ot = this.actd5_curr.value;
			 t = this.actd5_curr.value.addslashes();
		}

		if(ot.length == 0 && (kc != -1))
		{
			this.actd5_mouse_on_list = 0;
			this.actd5_removedisp();
		}
		else if((ot.length == 1) || this.actd5_fullRefresh ||
		       ((ot.length > 1) && !this.actd5_keywords.length) ||
		       ((ot.length > 1) && (this.actd5_keywords[0].substr(0, 1).toLowerCase() != ot.substr(0, 1).toLowerCase())))
		{
			var ot_ = ((ot.length > 1) && !this.actd5_fullRefresh) ? ot.substr(0, 1) : ot;

			if(this.actd5_suggesturl.length)
			{
				// create xmlhttprequest object:
				var http = null;
				if(typeof XMLHttpRequest != "undefined")
				{
					try
					{
						http = new XMLHttpRequest();
					}
					catch (e) { http = null; }
				}
				else
				{
					try
					{
						http = new ActiveXObject("Msxml2.XMLHTTP") ;
					}
					catch (e)
					{
						try
						{
							http = new ActiveXObject("Microsoft.XMLHTTP") ;
						}
						catch (e) { http = null; }
					}
				}

				if(http)
				{
					// For local debugging in Mozilla/Firefox:
					/*
					try
					{
						netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
					}
					catch (e) { }
					*/

					if(http.overrideMimeType)
						http.overrideMimeType("text/xml"); 

					http.open("POST", this.actd5_suggesturl + ot_, true);

					var obj = this;
					http.onreadystatechange = function(n)
					{
						if(http.readyState == 4)
						{
							if((http.status == 200) || (http.status == 0))
							{
								var text = http.responseText;
                                                                
                                                                var index1 = text.indexOf("<listdata>");

                                                                
                                                                
                                                                var index2 = (index1 == -1) ? text.length : text.indexOf("</listdata>", index1 + 10);
                                                                

								if(index1 == -1)
									index1 = 0;
								else
									index1 += 10;

								var tmpinfo = text.substring(index1, index2);
                                                                 if(tmpinfo=='')
                                                                     {
                                                                         img_obj.style.visibility="hidden";
                                                                         document.getElementById("msg_saveFAILED").innerHTML='No Suggestions available.';
                                                                         document.getElementById(text_id).value='';
                                                                     }

                                                                if(tmpinfo)
								{
									obj.actd5_keywords = tmpinfo.split(obj.ajax_delimiter);

									if(obj.item_delimiter && obj.item_delimiter.length)
									{
										var keyword_number = obj.actd5_keywords.length;
                                                                                
										for(var i = 0; i < keyword_number; i++)
										{
											var ca = obj.actd5_keywords[i], comma = ca.indexOf(obj.item_delimiter);
                                                                                            //alert(ca)
											if(comma != -1)
											{
												var ci = ca.split(",");
                                                                                                
												obj.actd5_keywords[i] = obj.actd5_keywords_init[i] = ci[0];
												obj.actd5_values[i]   = obj.actd5_values_init[i]   = ci[1];
											}
											else
											{
												
                                                                                                //obj.actd5_values[i] = obj.actd5_values_init[i] = "";
                                                                                                obj.actd5_values[i] = obj.actd5_values_init[i] = "";
                                                                                                
											}
										}
									}

									obj.done.call(obj, ot_, t);
                                                                        img_obj.style.visibility="hidden";
								}

							}
						}
					}
                                        //img_obj.style.visibility="hidden";
					http.send(null);
				}

				// xmlhttp object creation failed
				return;
			}
			else
			{
				this.done(ot, t);
			}
		}
		else
		{
			this.done(ot, t);
		}
	},

	done: function(ot, t)
	{
		if(ot.length < this.actd5_startcheck) return;

		var re = new RegExp(((!this.actd5_firstText && !this.actd5_firstMatch) ? "" : "^") + t, "i");

		this.actd5_total = 0;
		this.actd5_tomake = false;

		var al = this.actd5_keywords.length;

		for(var i = 0; i < al; i++)
		{
			this.actd5_bool[i] = false;
			if(re.test(this.actd5_keywords[i]))
			{
				this.actd5_total++;
				this.actd5_bool[i] = true;

				if(this.actd5_pre == i) this.actd5_tomake = true;
			}
		}

		if(!this.actd5_curr.value.length)
		{
			for(i = 0; i < al; i++)
			{
				this.actd5_keywords[i] = this.actd5_keywords_init[i];
				this.actd5_values[i] = this.actd5_values_init[i];
				this.actd5_bool[i] = true;
			}
		}
		else if(!this.actd5_firstText && this.actd5_firstMatch)
		{
			var tmp = [], tmpv = [];

			for(i = 0; i < al; i++)
			{
				if(this.actd5_bool[i])
				{
					tmp[tmp.length]   = this.actd5_keywords[i];
					tmpv[tmpv.length] = this.actd5_values[i];
				}
			}

			re = new RegExp(t, "i");

			for(i = 0; i < al; i++)
			{
				if(re.test(this.actd5_keywords[i]) && !this.actd5_bool[i])
				{
					this.actd5_total++;
					this.actd5_bool[i] = true;

					if(this.actd5_pre == i) this.actd5_tomake = true;

					tmp[tmp.length]   = this.actd5_keywords[i];
					tmpv[tmpv.length] = this.actd5_values[i];
				}
			}

			for(i = 0; i < al; i++)
			{
				if(!this.actd5_bool[i])
				{
					tmp[tmp.length]   = this.actd5_keywords[i];
					tmpv[tmpv.length] = this.actd5_values[i];
				}
			}

			for(i = 0; i < al; i++)
			{
				this.actd5_keywords[i] = tmp[i];
				this.actd5_values[i]   = tmpv[i];
			}

			for(i = 0; i < al; i++)
				this.actd5_bool[i] = (i < this.actd5_total) ? true : false;
		}

		if(this.actd5_timeOut > 0)
		  this.actd5_toid = setTimeout(function(){ this.actd5_mouse_on_list = 0; this.actd5_removedisp(); }, this.actd5_timeOut);

		this.actd5_generate();
	}
}

// Supplementary functions

// Add an event to the obj given
// event_name refers to the event trigger, without the "on", like click or mouseover
// func_name refers to the function callback when event is triggered
function addEvent(obj, event_name, func_ref)
{
//	if(obj.addEventListener && !window.opera)
//	{
//		obj.addEventListener(event_name, func_ref, true);
//	}
//	else
//	{
		obj["on" + event_name] = func_ref;
	//}
}

// Removes an event from the object
function removeEvent(obj, event_name, func_ref)
{
	if(obj.removeEventListener && !window.opera)
	{
		obj.removeEventListener(event_name, func_ref, true);
	}
	else
	{
		obj["on" + event_name] = null;
	}
}

// Stop an event from bubbling up the event DOM
function stopEvent(event)
{
	event = event || window.event;

	if(event)
	{
		if(event.stopPropagation) event.stopPropagation();
		if(event.preventDefault) event.preventDefault();

		if(typeof event.cancelBubble != "undefined")
		{
			event.cancelBubble = true;
			event.returnValue = false;
		}
	}

	return false;
}

// Get the obj that triggers off the event
function getTargetElement(event)
{
	event = event || window.event;
	return event.srcElement || event.target;
}

// Sets the caret position to l in the object
function setCaret(obj, l)
{
	obj.focus();

	if(obj.setSelectionRange)
	{
		obj.setSelectionRange(l, l);
	}
	else if(obj.createTextRange)
	{
		m = obj.createTextRange();
		m.moveStart("character", l);
		m.collapse();
		m.select();
	}
}

// String functions
String.prototype.addslashes = function() { return this.replace(/(["\\\.\|\[\]\^\*\+\?\$\(\)])/g, "\\$1"); }

String.prototype.trim = function () { return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1"); };

// Offset position from top of the screen
function curTop(obj)
{
	var toreturn = 0;
	while(obj)
	{
		toreturn += obj.offsetTop;
		obj = obj.offsetParent;
	}

	return toreturn;
}

// Offset position from left of the screen
function curLeft(obj)
{
	var toreturn = 0;
	while(obj)
	{
		toreturn += obj.offsetLeft;
		obj = obj.offsetParent;
	}

	return toreturn;
}

// Image installation
function replaceHTML(obj, oImg)
{
	var el = obj.childNodes[0];
	while(el)
	{
		obj.removeChild(el);
		el = obj.childNodes[0];
	}

	obj.appendChild(oImg);
}