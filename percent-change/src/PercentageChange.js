import React from "react";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import MathJax from "react-mathjax";

class PercentageChange extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      initPrice: "",
      finalPrice: "",
      formula: "",
      change: "",
    };
  }

  handleChange = (event) => {
    if (event.target.name === "initPrice") {
      this.setState({ initPrice: event.target.value });
    } else {
      this.setState({ finalPrice: event.target.value });
    }
  };

  calculate = (event) => {
    const v1 = parseFloat(this.state.initPrice);
    const v2 = parseFloat(this.state.finalPrice);
    let result;
    let equation;
    if (v1 > v2) {
      result = ((v1 - v2) / Math.abs(v1)) * 100;
      result = result.toFixed(2) + "% decrease";
      equation = `\\frac{${v1} - ${v2}}{${Math.abs(v1)}} * 100`;
    } else if (v1 < v2) {
      result = ((v2 - v1) / Math.abs(v1)) * 100;
      result = result.toFixed(2) + "% increase";
      equation = `\\frac{${v2} - ${v1}}{${Math.abs(v1)}} * 100`;
    } else if (v1 === v2) {
      result = ((v1 - v2) / Math.abs(v1)) * 100;
      result = result.toFixed(2) + "% decrease";
      equation = `\\frac{${v1} - ${v2}}{${Math.abs(v1)}} * 100`;
      result = "0% change";
    }
    this.setState({ change: result, formula: equation });
    event.preventDefault();
  };

  render() {
    return (
      <Container component="main" maxWidth="lg">
        <CssBaseline />
        <Typography component="h1" variant="h2" align="center">
          % Change
        </Typography>
        <form onSubmit={this.calculate} align="center">
          <TextField
            value={this.state.initPrice}
            onChange={this.handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="initPrice"
            label="Initial Price"
            name="initPrice"
            autoComplete="initPrice"
            autoFocus
          />
          <TextField
            value={this.state.finalPrice}
            onChange={this.handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="finalPrice"
            label="Final Price"
            type="finalPrice"
            id="finalPrice"
            autoComplete="finalPrice"
          />
          <Button
            size="large"
            type="submit"
            variant="contained"
            color="primary"
            align="center"
          >
            Calculate
          </Button>
        </form>
        <Typography component="h1" variant="h4" align="center">
          <MathJax.Provider>
            <div>
              <MathJax.Node inline formula={this.state.formula} />
            </div>
          </MathJax.Provider>
        </Typography>
        <Typography component="h1" variant="h4" align="center">
          {this.state.change}
        </Typography>
      </Container>
    );
  }
}

export default PercentageChange;
